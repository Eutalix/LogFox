package com.f0x1d.logfox.feature.remote.devices.presentation.list

import com.f0x1d.logfox.core.tea.ReduceResult
import com.f0x1d.logfox.core.tea.Reducer
import com.f0x1d.logfox.core.tea.noSideEffects
import com.f0x1d.logfox.core.tea.withSideEffects
import com.f0x1d.logfox.feature.remote.devices.api.model.ConnectionState
import javax.inject.Inject

internal class RemoteDevicesReducer @Inject constructor() :
    Reducer<RemoteDevicesState, RemoteDevicesCommand, RemoteDevicesSideEffect> {

    override fun reduce(
        state: RemoteDevicesState,
        command: RemoteDevicesCommand,
    ): ReduceResult<RemoteDevicesState, RemoteDevicesSideEffect> = when (command) {
        is RemoteDevicesCommand.DevicesLoaded -> {
            state.copy(devices = command.devices).noSideEffects()
        }

        is RemoteDevicesCommand.ConnectionStateChanged -> {
            val sideEffects = when (val newState = command.state) {
                is ConnectionState.Error -> listOf(RemoteDevicesSideEffect.ShowError(newState.message))
                else -> emptyList()
            }
            state.copy(connectionState = command.state).withSideEffects(*sideEffects.toTypedArray())
        }

        is RemoteDevicesCommand.ConnectToDevice -> {
            state.withSideEffects(RemoteDevicesSideEffect.PerformConnect(command.device))
        }

        is RemoteDevicesCommand.Disconnect -> {
            state.withSideEffects(RemoteDevicesSideEffect.PerformDisconnect)
        }

        is RemoteDevicesCommand.DeleteDevice -> {
            state.withSideEffects(RemoteDevicesSideEffect.PerformDelete(command.device))
        }

        is RemoteDevicesCommand.AddDeviceClicked -> {
            state.withSideEffects(RemoteDevicesSideEffect.NavigateToAddDevice)
        }

        is RemoteDevicesCommand.EditDeviceClicked -> {
            state.withSideEffects(RemoteDevicesSideEffect.NavigateToEditDevice(command.device.id))
        }

        is RemoteDevicesCommand.StartLoggingClicked -> {
            if (state.connectionState is ConnectionState.Connected) {
                state.withSideEffects(RemoteDevicesSideEffect.NavigateToLogs)
            } else {
                state.noSideEffects()
            }
        }
    }
}