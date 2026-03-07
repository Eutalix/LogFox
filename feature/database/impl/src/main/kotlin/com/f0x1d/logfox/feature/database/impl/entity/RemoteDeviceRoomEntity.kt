package com.f0x1d.logfox.feature.database.impl.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_devices")
data class RemoteDeviceRoomEntity(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val name: String,
    val host: String,
    val port: Int,
)