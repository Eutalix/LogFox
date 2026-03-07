package com.f0x1d.logfox.feature.remote.devices.impl.domain

import com.f0x1d.logfox.feature.remote.devices.api.data.RemoteDevicesRepository
import com.f0x1d.logfox.feature.remote.devices.api.domain.DeleteRemoteDeviceUseCase
import com.f0x1d.logfox.feature.remote.devices.api.model.RemoteDevice
import javax.inject.Inject

internal class DeleteRemoteDeviceUseCaseImpl @Inject constructor(
    private val repository: RemoteDevicesRepository,
) : DeleteRemoteDeviceUseCase {

    override suspend fun invoke(device: RemoteDevice) {
        repository.deleteDevice(device)
    }
}