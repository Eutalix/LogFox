package com.f0x1d.logfox.feature.remote.devices.presentation.edit

internal data class EditRemoteDeviceState(
    val deviceId: Long?,
    val name: String,
    val host: String,
    val port: String,
    val isLoading: Boolean,
)