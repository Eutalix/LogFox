package com.f0x1d.logfox.feature.remote.devices.api.domain

import com.f0x1d.logfox.feature.remote.devices.api.model.RemoteDevice

interface GetRemoteDeviceByIdUseCase {
    suspend operator fun invoke(id: Long): RemoteDevice?
}