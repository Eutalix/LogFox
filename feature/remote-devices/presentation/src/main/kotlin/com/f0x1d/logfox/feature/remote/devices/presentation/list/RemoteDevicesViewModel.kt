package com.f0x1d.logfox.feature.remote.devices.presentation.list

import com.f0x1d.logfox.core.tea.BaseStoreViewModel
import com.f0x1d.logfox.feature.remote.devices.api.model.ConnectionState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class RemoteDevicesViewModel @Inject constructor(
    reducer: RemoteDevicesReducer,
    effectHandler: RemoteDevicesEffectHandler,
    viewStateMapper: RemoteDevicesViewStateMapper,
) : BaseStoreViewModel<RemoteDevicesViewState, RemoteDevicesState, RemoteDevicesCommand, RemoteDevicesSideEffect>(
    initialState = RemoteDevicesState(
        devices = emptyList(),
        connectionState = ConnectionState.Disconnected,
    ),
    reducer = reducer,
    effectHandlers = listOf(effectHandler),
    viewStateMapper = viewStateMapper,
    initialSideEffects = listOf(
        RemoteDevicesSideEffect.LoadDevices,
        RemoteDevicesSideEffect.ObserveConnectionState,
    ),
)