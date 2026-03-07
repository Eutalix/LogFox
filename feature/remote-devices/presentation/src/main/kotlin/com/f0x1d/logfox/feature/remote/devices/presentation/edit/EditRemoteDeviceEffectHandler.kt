package com.f0x1d.logfox.feature.remote.devices.presentation.edit

import com.f0x1d.logfox.core.tea.EffectHandler
import com.f0x1d.logfox.feature.remote.devices.api.domain.GetRemoteDeviceByIdUseCase
import com.f0x1d.logfox.feature.remote.devices.api.domain.SaveRemoteDeviceUseCase
import javax.inject.Inject

internal class EditRemoteDeviceEffectHandler @Inject constructor(
    private val getRemoteDeviceByIdUseCase: GetRemoteDeviceByIdUseCase,
    private val saveRemoteDeviceUseCase: SaveRemoteDeviceUseCase,
) : EffectHandler<EditRemoteDeviceSideEffect, EditRemoteDeviceCommand> {

    override suspend fun handle(
        effect: EditRemoteDeviceSideEffect,
        onCommand: suspend (EditRemoteDeviceCommand) -> Unit,
    ) {
        when (effect) {
            is EditRemoteDeviceSideEffect.LoadDevice -> {
                val device = effect.deviceId?.let { getRemoteDeviceByIdUseCase(it) }
                onCommand(EditRemoteDeviceCommand.DeviceLoaded(device))
            }

            is EditRemoteDeviceSideEffect.PerformSave -> {
                saveRemoteDeviceUseCase(effect.device)
                onCommand(EditRemoteDeviceCommand.SaveCompleted)
            }

            // UI side effects - handled by Fragment
            else -> Unit
        }
    }
}