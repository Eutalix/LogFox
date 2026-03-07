package com.f0x1d.logfox.feature.remote.devices.impl.data

import com.f0x1d.logfox.core.di.IODispatcher
import com.f0x1d.logfox.feature.database.api.data.RemoteDeviceDataSource
import com.f0x1d.logfox.feature.remote.devices.api.data.RemoteDevicesRepository
import com.f0x1d.logfox.feature.remote.devices.api.model.ConnectionState
import com.f0x1d.logfox.feature.remote.devices.api.model.RemoteDevice
import com.f0x1d.logfox.feature.remote.devices.impl.adb.AdbConnection
import com.f0x1d.logfox.feature.remote.devices.impl.adb.RemoteTerminal
import com.f0x1d.logfox.feature.remote.devices.impl.mapper.toEntity
import com.f0x1d.logfox.feature.remote.devices.impl.mapper.toRemoteDevice
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class RemoteDevicesRepositoryImpl @Inject constructor(
    private val remoteDeviceDataSource: RemoteDeviceDataSource,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
) : RemoteDevicesRepository {

    private val _connectionState = MutableStateFlow<ConnectionState>(ConnectionState.Disconnected)
    override val connectionState: StateFlow<ConnectionState> = _connectionState.asStateFlow()

    private var currentConnection: AdbConnection? = null
    private var currentTerminal: RemoteTerminal? = null

    override fun getAllDevices(): Flow<List<RemoteDevice>> =
        remoteDeviceDataSource.getAll().map { entities ->
            entities.map { it.toRemoteDevice() }
        }

    override suspend fun getDeviceById(id: Long): RemoteDevice? =
        remoteDeviceDataSource.getById(id)?.toRemoteDevice()

    override suspend fun saveDevice(device: RemoteDevice): Long {
        val entity = device.toEntity()
        return if (entity.id == null) {
            remoteDeviceDataSource.insert(entity)
        } else {
            remoteDeviceDataSource.update(entity)
            entity.id
        }
    }

    override suspend fun deleteDevice(device: RemoteDevice) {
        remoteDeviceDataSource.delete(device.toEntity())
    }

    override suspend fun connect(device: RemoteDevice) = withContext(ioDispatcher) {
        Timber.d("Connecting to device: ${device.name}")
        _connectionState.value = ConnectionState.Connecting

        runCatching {
            // Disconnect existing connection if any
            currentConnection?.close()

            val connection = AdbConnection(device.host, device.port)
            connection.connect()

            currentConnection = connection
            currentTerminal = RemoteTerminal(device, connection)

            _connectionState.value = ConnectionState.Connected(device)
            Timber.d("Connected to device: ${device.name}")
        }.onFailure { throwable ->
            Timber.e(throwable, "Failed to connect to device: ${device.name}")
            _connectionState.value = ConnectionState.Error(
                throwable.message ?: "Unknown error"
            )
        }
    }

    override suspend fun disconnect() = withContext(ioDispatcher) {
        Timber.d("Disconnecting from device")
        currentTerminal?.exit()
        currentConnection?.close()
        currentConnection = null
        currentTerminal = null
        _connectionState.value = ConnectionState.Disconnected
    }

    fun getActiveTerminal(): RemoteTerminal? = currentTerminal
}