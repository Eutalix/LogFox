package com.f0x1d.logfox.feature.remote.devices.impl.domain

import com.f0x1d.logfox.feature.remote.devices.api.data.RemoteDevicesRepository
import com.f0x1d.logfox.feature.remote.devices.api.domain.GetConnectionStateFlowUseCase
import com.f0x1d.logfox.feature.remote.devices.api.model.ConnectionState
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

internal class GetConnectionStateFlowUseCaseImpl @Inject constructor(
    private val repository: RemoteDevicesRepository,
) : GetConnectionStateFlowUseCase {

    override fun invoke(): StateFlow<ConnectionState> = repository.connectionState
}