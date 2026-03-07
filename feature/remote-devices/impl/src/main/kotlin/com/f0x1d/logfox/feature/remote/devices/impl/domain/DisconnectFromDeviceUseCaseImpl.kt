package com.f0x1d.logfox.feature.remote.devices.impl.domain

import com.f0x1d.logfox.feature.remote.devices.api.data.RemoteDevicesRepository
import com.f0x1d.logfox.feature.remote.devices.api.domain.DisconnectFromDeviceUseCase
import javax.inject.Inject

internal class DisconnectFromDeviceUseCaseImpl @Inject constructor(
    private val repository: RemoteDevicesRepository,
) : DisconnectFromDeviceUseCase {

    override suspend fun invoke() {
        repository.disconnect()
    }
}