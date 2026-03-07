package com.f0x1d.logfox.feature.remote.devices.presentation.list

import com.f0x1d.logfox.feature.remote.devices.api.model.ConnectionState
import com.f0x1d.logfox.feature.remote.devices.presentation.list.model.RemoteDeviceItem

internal data class RemoteDevicesViewState(
    val devices: List<RemoteDeviceItem>,
    val connectionState: ConnectionState,
    val isConnected: Boolean,
    val isConnecting: Boolean,
    val connectedDeviceId: Long?,
)