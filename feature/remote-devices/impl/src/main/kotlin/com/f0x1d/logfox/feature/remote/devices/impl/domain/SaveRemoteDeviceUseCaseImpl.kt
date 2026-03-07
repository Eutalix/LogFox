package com.f0x1d.logfox.feature.remote.devices.impl.domain

import com.f0x1d.logfox.feature.remote.devices.api.data.RemoteDevicesRepository
import com.f0x1d.logfox.feature.remote.devices.api.domain.SaveRemoteDeviceUseCase
import com.f0x1d.logfox.feature.remote.devices.api.model.RemoteDevice
import javax.inject.Inject

internal class SaveRemoteDeviceUseCaseImpl @Inject constructor(
    private val repository: RemoteDevicesRepository,
) : SaveRemoteDeviceUseCase {

    override suspend fun invoke(device: RemoteDevice): Long = repository.saveDevice(device)
}