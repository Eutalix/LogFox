package com.f0x1d.logfox.feature.remote.devices.api.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RemoteDevice(
    val id: Long,
    val name: String,
    val host: String,
    val port: Int = DEFAULT_ADB_PORT,
) : Parcelable {

    val address: String get() = "$host:$port"

    companion object {
        const val DEFAULT_ADB_PORT = 5555
    }
}