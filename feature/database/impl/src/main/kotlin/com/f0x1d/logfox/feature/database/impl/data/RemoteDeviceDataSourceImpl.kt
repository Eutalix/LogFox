package com.f0x1d.logfox.feature.database.impl.data

import com.f0x1d.logfox.feature.database.api.data.RemoteDeviceDataSource
import com.f0x1d.logfox.feature.database.api.entity.RemoteDeviceEntity
import com.f0x1d.logfox.feature.database.impl.data.dao.RemoteDeviceDao
import com.f0x1d.logfox.feature.database.impl.mapper.toEntity
import com.f0x1d.logfox.feature.database.impl.mapper.toRoomEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class RemoteDeviceDataSourceImpl @Inject constructor(
    private val remoteDeviceDao: RemoteDeviceDao,
) : RemoteDeviceDataSource {

    override fun getAll(): Flow<List<RemoteDeviceEntity>> =
        remoteDeviceDao.getAll().map { entities ->
            entities.map { it.toEntity() }
        }

    override suspend fun getById(id: Long): RemoteDeviceEntity? =
        remoteDeviceDao.getById(id)?.toEntity()

    override suspend fun insert(entity: RemoteDeviceEntity): Long =
        remoteDeviceDao.insert(entity.toRoomEntity())

    override suspend fun update(entity: RemoteDeviceEntity) {
        remoteDeviceDao.update(entity.toRoomEntity())
    }

    override suspend fun delete(entity: RemoteDeviceEntity) {
        remoteDeviceDao.delete(entity.toRoomEntity())
    }
}