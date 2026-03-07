package com.f0x1d.logfox.feature.remote.devices.presentation.list.model

import com.f0x1d.logfox.core.recycler.Identifiable

data class RemoteDeviceItem(
    val deviceId: Long,
    val name: String,
    val address: String,
    val isConnected: Boolean,
) : Identifiable {
    override val id: Any get() = deviceId
}