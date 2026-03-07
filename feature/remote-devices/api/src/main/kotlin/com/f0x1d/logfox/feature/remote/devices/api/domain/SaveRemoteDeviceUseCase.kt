package com.f0x1d.logfox.feature.remote.devices.api.domain

import com.f0x1d.logfox.feature.remote.devices.api.model.RemoteDevice

interface SaveRemoteDeviceUseCase {
    suspend operator fun invoke(device: RemoteDevice): Long
}