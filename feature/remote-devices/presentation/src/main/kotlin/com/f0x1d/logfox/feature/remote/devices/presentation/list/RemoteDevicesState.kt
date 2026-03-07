package com.f0x1d.logfox.feature.remote.devices.presentation.list

import com.f0x1d.logfox.feature.remote.devices.api.model.ConnectionState
import com.f0x1d.logfox.feature.remote.devices.api.model.RemoteDevice

internal data class RemoteDevicesState(
    val devices: List<RemoteDevice>,
    val connectionState: ConnectionState,
)