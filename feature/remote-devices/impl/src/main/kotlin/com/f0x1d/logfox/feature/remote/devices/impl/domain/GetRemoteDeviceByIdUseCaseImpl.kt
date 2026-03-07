package com.f0x1d.logfox.feature.remote.devices.impl.domain

import com.f0x1d.logfox.feature.remote.devices.api.data.RemoteDevicesRepository
import com.f0x1d.logfox.feature.remote.devices.api.domain.GetRemoteDeviceByIdUseCase
import com.f0x1d.logfox.feature.remote.devices.api.model.RemoteDevice
import javax.inject.Inject

internal class GetRemoteDeviceByIdUseCaseImpl @Inject constructor(
    private val repository: RemoteDevicesRepository,
) : GetRemoteDeviceByIdUseCase {

    override suspend fun invoke(id: Long): RemoteDevice? = repository.getDeviceById(id)
}