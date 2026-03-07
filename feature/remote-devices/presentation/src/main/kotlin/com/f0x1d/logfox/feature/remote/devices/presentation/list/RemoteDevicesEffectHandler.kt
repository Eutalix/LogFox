package com.f0x1d.logfox.feature.remote.devices.presentation.list

import com.f0x1d.logfox.core.tea.EffectHandler
import com.f0x1d.logfox.feature.remote.devices.api.domain.ConnectToDeviceUseCase
import com.f0x1d.logfox.feature.remote.devices.api.domain.DeleteRemoteDeviceUseCase
import com.f0x1d.logfox.feature.remote.devices.api.domain.DisconnectFromDeviceUseCase
import com.f0x1d.logfox.feature.remote.devices.api.domain.GetAllRemoteDevicesFlowUseCase
import com.f0x1d.logfox.feature.remote.devices.api.domain.GetConnectionStateFlowUseCase
import javax.inject.Inject

internal class RemoteDevicesEffectHandler @Inject constructor(
    private val getAllRemoteDevicesFlowUseCase: GetAllRemoteDevicesFlowUseCase,
    private val getConnectionStateFlowUseCase: GetConnectionStateFlowUseCase,
    private val connectToDeviceUseCase: ConnectToDeviceUseCase,
    private val disconnectFromDeviceUseCase: DisconnectFromDeviceUseCase,
    private val deleteRemoteDeviceUseCase: DeleteRemoteDeviceUseCase,
) : EffectHandler<RemoteDevicesSideEffect, RemoteDevicesCommand> {

    override suspend fun handle(
        effect: RemoteDevicesSideEffect,
        onCommand: suspend (RemoteDevicesCommand) -> Unit,
    ) {
        when (effect) {
            is RemoteDevicesSideEffect.LoadDevices -> {
                getAllRemoteDevicesFlowUseCase().collect { devices ->
                    onCommand(RemoteDevicesCommand.DevicesLoaded(devices))
                }
            }

            is RemoteDevicesSideEffect.ObserveConnectionState -> {
                getConnectionStateFlowUseCase().collect { state ->
                    onCommand(RemoteDevicesCommand.ConnectionStateChanged(state))
                }
            }

            is RemoteDevicesSideEffect.PerformConnect -> {
                connectToDeviceUseCase(effect.device)
            }

            is RemoteDevicesSideEffect.PerformDisconnect -> {
                disconnectFromDeviceUseCase()
            }

            is RemoteDevicesSideEffect.PerformDelete -> {
                deleteRemoteDeviceUseCase(effect.device)
            }

            // UI side effects - handled by Fragment
            else -> Unit
        }
    }
}