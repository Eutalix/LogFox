package com.f0x1d.logfox.feature.remote.devices.presentation.list

import com.f0x1d.logfox.feature.remote.devices.api.model.RemoteDevice

internal sealed interface RemoteDevicesSideEffect {
    // Business logic side effects - handled by EffectHandler
    data object LoadDevices : RemoteDevicesSideEffect
    data object ObserveConnectionState : RemoteDevicesSideEffect
    data class PerformConnect(val device: RemoteDevice) : RemoteDevicesSideEffect
    data object PerformDisconnect : RemoteDevicesSideEffect
    data class PerformDelete(val device: RemoteDevice) : RemoteDevicesSideEffect

    // UI side effects - handled by Fragment
    data object NavigateToAddDevice : RemoteDevicesSideEffect
    data class NavigateToEditDevice(val deviceId: Long) : RemoteDevicesSideEffect
    data object NavigateToLogs : RemoteDevicesSideEffect
    data class ShowError(val message: String) : RemoteDevicesSideEffect
}