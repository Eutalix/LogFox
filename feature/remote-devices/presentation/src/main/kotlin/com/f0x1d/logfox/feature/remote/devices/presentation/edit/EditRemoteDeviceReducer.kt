package com.f0x1d.logfox.feature.remote.devices.presentation.edit

import com.f0x1d.logfox.core.tea.ReduceResult
import com.f0x1d.logfox.core.tea.Reducer
import com.f0x1d.logfox.core.tea.noSideEffects
import com.f0x1d.logfox.core.tea.withSideEffects
import com.f0x1d.logfox.feature.remote.devices.api.model.RemoteDevice
import javax.inject.Inject

internal class EditRemoteDeviceReducer @Inject constructor() :
    Reducer<EditRemoteDeviceState, EditRemoteDeviceCommand, EditRemoteDeviceSideEffect> {

    override fun reduce(
        state: EditRemoteDeviceState,
        command: EditRemoteDeviceCommand,
    ): ReduceResult<EditRemoteDeviceState, EditRemoteDeviceSideEffect> = when (command) {
        is EditRemoteDeviceCommand.DeviceLoaded -> {
            val device = command.device
            if (device != null) {
                state.copy(
                    deviceId = device.id,
                    name = device.name,
                    host = device.host,
                    port = device.port.toString(),
                    isLoading = false,
                ).noSideEffects()
            } else {
                state.copy(isLoading = false).noSideEffects()
            }
        }

        is EditRemoteDeviceCommand.UpdateName -> {
            state.copy(name = command.name).noSideEffects()
        }

        is EditRemoteDeviceCommand.UpdateHost -> {
            state.copy(host = command.host).noSideEffects()
        }

        is EditRemoteDeviceCommand.UpdatePort -> {
            state.copy(port = command.port).noSideEffects()
        }

        is EditRemoteDeviceCommand.Save -> {
            val port = state.port.toIntOrNull() ?: RemoteDevice.DEFAULT_ADB_PORT
            val device = RemoteDevice(
                id = state.deviceId ?: 0L,
                name = state.name.ifBlank { state.host },
                host = state.host,
                port = port,
            )

            if (state.host.isBlank()) {
                state.withSideEffects(EditRemoteDeviceSideEffect.ShowError("Host is required"))
            } else {
                state.withSideEffects(EditRemoteDeviceSideEffect.PerformSave(device))
            }
        }

        is EditRemoteDeviceCommand.SaveCompleted -> {
            state.withSideEffects(EditRemoteDeviceSideEffect.NavigateBack)
        }
    }
}