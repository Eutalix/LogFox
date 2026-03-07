package com.f0x1d.logfox.feature.remote.devices.api.domain

import com.f0x1d.logfox.feature.remote.devices.api.model.RemoteDevice

interface DeleteRemoteDeviceUseCase {
    suspend operator fun invoke(device: RemoteDevice)
}