package com.f0x1d.logfox.feature.remote.devices.impl.domain

import com.f0x1d.logfox.feature.remote.devices.api.data.RemoteDevicesRepository
import com.f0x1d.logfox.feature.remote.devices.api.domain.ConnectToDeviceUseCase
import com.f0x1d.logfox.feature.remote.devices.api.model.RemoteDevice
import javax.inject.Inject

internal class ConnectToDeviceUseCaseImpl @Inject constructor(
    private val repository: RemoteDevicesRepository,
) : ConnectToDeviceUseCase {

    override suspend fun invoke(device: RemoteDevice) {
        repository.connect(device)
    }
}