package com.f0x1d.logfox.feature.remote.devices.presentation.edit

import com.f0x1d.logfox.feature.remote.devices.api.model.RemoteDevice

internal sealed interface EditRemoteDeviceCommand {
    data class DeviceLoaded(val device: RemoteDevice?) : EditRemoteDeviceCommand

    data class UpdateName(val name: String) : EditRemoteDeviceCommand
    data class UpdateHost(val host: String) : EditRemoteDeviceCommand
    data class UpdatePort(val port: String) : EditRemoteDeviceCommand

    data object Save : EditRemoteDeviceCommand
    data object SaveCompleted : EditRemoteDeviceCommand
}