package com.f0x1d.logfox.feature.remote.devices.api.domain

import com.f0x1d.logfox.feature.remote.devices.api.model.RemoteDevice
import kotlinx.coroutines.flow.Flow

interface GetAllRemoteDevicesFlowUseCase {
    operator fun invoke(): Flow<List<RemoteDevice>>
}