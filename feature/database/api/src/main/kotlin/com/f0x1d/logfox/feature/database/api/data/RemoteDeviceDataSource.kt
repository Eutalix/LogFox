package com.f0x1d.logfox.feature.database.api.data

import com.f0x1d.logfox.feature.database.api.entity.RemoteDeviceEntity
import kotlinx.coroutines.flow.Flow

interface RemoteDeviceDataSource {

    fun getAll(): Flow<List<RemoteDeviceEntity>>

    suspend fun getById(id: Long): RemoteDeviceEntity?

    suspend fun insert(entity: RemoteDeviceEntity): Long

    suspend fun update(entity: RemoteDeviceEntity)

    suspend fun delete(entity: RemoteDeviceEntity)
}