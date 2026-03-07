package com.f0x1d.logfox.feature.remote.devices.presentation.list

import com.f0x1d.logfox.feature.remote.devices.api.model.ConnectionState
import com.f0x1d.logfox.feature.remote.devices.api.model.RemoteDevice

internal sealed interface RemoteDevicesCommand {
    data class DevicesLoaded(val devices: List<RemoteDevice>) : RemoteDevicesCommand
    data class ConnectionStateChanged(val state: ConnectionState) : RemoteDevicesCommand

    data class ConnectToDevice(val device: RemoteDevice) : RemoteDevicesCommand
    data object Disconnect : RemoteDevicesCommand
    data class DeleteDevice(val device: RemoteDevice) : RemoteDevicesCommand

    data object AddDeviceClicked : RemoteDevicesCommand
    data class EditDeviceClicked(val device: RemoteDevice) : RemoteDevicesCommand

    data object StartLoggingClicked : RemoteDevicesCommand
}