package com.f0x1d.logfox.feature.database.impl.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.f0x1d.logfox.feature.database.impl.entity.RemoteDeviceRoomEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RemoteDeviceDao {

    @Query("SELECT * FROM remote_devices")
    fun getAll(): Flow<List<RemoteDeviceRoomEntity>>

    @Query("SELECT * FROM remote_devices WHERE id = :id")
    suspend fun getById(id: Long): RemoteDeviceRoomEntity?

    @Insert
    suspend fun insert(entity: RemoteDeviceRoomEntity): Long

    @Update
    suspend fun update(entity: RemoteDeviceRoomEntity)

    @Delete
    suspend fun delete(entity: RemoteDeviceRoomEntity)
}