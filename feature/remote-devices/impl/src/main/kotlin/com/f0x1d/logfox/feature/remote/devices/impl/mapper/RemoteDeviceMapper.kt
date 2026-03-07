package com.f0x1d.logfox.feature.remote.devices.impl.mapper

import com.f0x1d.logfox.feature.database.api.entity.RemoteDeviceEntity
import com.f0x1d.logfox.feature.remote.devices.api.model.RemoteDevice

internal fun RemoteDeviceEntity.toRemoteDevice() = RemoteDevice(
    id = id ?: 0L,
    name = name,
    host = host,
    port = port,
)

internal fun RemoteDevice.toEntity() = RemoteDeviceEntity(
    id = if (id == 0L) null else id,
    name = name,
    host = host,
    port = port,
)