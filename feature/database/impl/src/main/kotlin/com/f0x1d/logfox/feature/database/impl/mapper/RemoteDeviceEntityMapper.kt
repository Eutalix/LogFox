package com.f0x1d.logfox.feature.database.impl.mapper

import com.f0x1d.logfox.feature.database.api.entity.RemoteDeviceEntity
import com.f0x1d.logfox.feature.database.impl.entity.RemoteDeviceRoomEntity

internal fun RemoteDeviceRoomEntity.toEntity() = RemoteDeviceEntity(
    id = id,
    name = name,
    host = host,
    port = port,
)

internal fun RemoteDeviceEntity.toRoomEntity() = RemoteDeviceRoomEntity(
    id = id,
    name = name,
    host = host,
    port = port,
)