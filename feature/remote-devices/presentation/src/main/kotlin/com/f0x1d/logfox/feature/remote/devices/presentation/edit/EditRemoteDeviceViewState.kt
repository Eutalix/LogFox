package com.f0x1d.logfox.feature.remote.devices.presentation.edit

internal data class EditRemoteDeviceViewState(
    val name: String,
    val host: String,
    val port: String,
    val isEditing: Boolean,
    val isLoading: Boolean,
)