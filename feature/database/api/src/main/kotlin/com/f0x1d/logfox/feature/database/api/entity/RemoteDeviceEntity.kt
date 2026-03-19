package com.f0x1d.logfox.feature.database.api.entity

data class RemoteDeviceEntity(
    val id: Long?,
    val name: String,
    val host: String,
    val port: Int,
)