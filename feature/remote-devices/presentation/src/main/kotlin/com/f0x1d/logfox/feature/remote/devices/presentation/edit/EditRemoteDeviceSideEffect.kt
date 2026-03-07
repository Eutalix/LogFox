package com.f0x1d.logfox.feature.remote.devices.presentation.edit

import com.f0x1d.logfox.feature.remote.devices.api.model.RemoteDevice

internal sealed interface EditRemoteDeviceSideEffect {
    // Business logic side effects - handled by EffectHandler
    data class LoadDevice(val deviceId: Long?) : EditRemoteDeviceSideEffect
    data class PerformSave(val device: RemoteDevice) : EditRemoteDeviceSideEffect

    // UI side effects - handled by Fragment
    data object NavigateBack : EditRemoteDeviceSideEffect
    data class ShowError(val message: String) : EditRemoteDeviceSideEffect
}