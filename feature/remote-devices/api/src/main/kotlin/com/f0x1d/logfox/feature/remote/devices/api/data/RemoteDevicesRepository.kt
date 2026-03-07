package com.f0x1d.logfox.feature.remote.devices.api.data

import com.f0x1d.logfox.feature.remote.devices.api.model.ConnectionState
import com.f0x1d.logfox.feature.remote.devices.api.model.RemoteDevice
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface RemoteDevicesRepository {

    val connectionState: StateFlow<ConnectionState>

    fun getAllDevices(): Flow<List<RemoteDevice>>

    suspend fun getDeviceById(id: Long): RemoteDevice?

    suspend fun saveDevice(device: RemoteDevice): Long

    suspend fun deleteDevice(device: RemoteDevice)

    suspend fun connect(device: RemoteDevice)

    suspend fun disconnect()
}