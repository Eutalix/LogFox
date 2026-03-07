package com.f0x1d.logfox.feature.remote.devices.impl.domain

import com.f0x1d.logfox.feature.remote.devices.api.data.RemoteDevicesRepository
import com.f0x1d.logfox.feature.remote.devices.api.domain.GetAllRemoteDevicesFlowUseCase
import com.f0x1d.logfox.feature.remote.devices.api.model.RemoteDevice
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetAllRemoteDevicesFlowUseCaseImpl @Inject constructor(
    private val repository: RemoteDevicesRepository,
) : GetAllRemoteDevicesFlowUseCase {

    override fun invoke(): Flow<List<RemoteDevice>> = repository.getAllDevices()
}