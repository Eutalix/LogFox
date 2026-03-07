package com.f0x1d.logfox.feature.remote.devices.presentation.list

import com.f0x1d.logfox.core.tea.ViewStateMapper
import com.f0x1d.logfox.feature.remote.devices.api.model.ConnectionState
import com.f0x1d.logfox.feature.remote.devices.presentation.list.model.toItem
import javax.inject.Inject

internal class RemoteDevicesViewStateMapper @Inject constructor() :
    ViewStateMapper<RemoteDevicesState, RemoteDevicesViewState> {

    override fun map(state: RemoteDevicesState): RemoteDevicesViewState {
        val connectedDeviceId = (state.connectionState as? ConnectionState.Connected)?.device?.id

        return RemoteDevicesViewState(
            devices = state.devices.map { device ->
                device.toItem(isConnected = device.id == connectedDeviceId)
            },
            connectionState = state.connectionState,
            isConnected = state.connectionState is ConnectionState.Connected,
            isConnecting = state.connectionState is ConnectionState.Connecting,
            connectedDeviceId = connectedDeviceId,
        )
    }
}