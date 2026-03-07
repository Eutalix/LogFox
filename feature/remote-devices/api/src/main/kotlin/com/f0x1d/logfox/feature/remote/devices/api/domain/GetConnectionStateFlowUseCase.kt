package com.f0x1d.logfox.feature.remote.devices.api.domain

import com.f0x1d.logfox.feature.remote.devices.api.model.ConnectionState
import kotlinx.coroutines.flow.StateFlow

interface GetConnectionStateFlowUseCase {
    operator fun invoke(): StateFlow<ConnectionState>
}