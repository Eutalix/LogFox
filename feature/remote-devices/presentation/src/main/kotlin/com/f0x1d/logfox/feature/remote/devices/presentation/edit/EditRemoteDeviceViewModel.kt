package com.f0x1d.logfox.feature.remote.devices.presentation.edit

import androidx.lifecycle.SavedStateHandle
import com.f0x1d.logfox.core.tea.BaseStoreViewModel
import com.f0x1d.logfox.feature.remote.devices.api.model.RemoteDevice
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class EditRemoteDeviceViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    reducer: EditRemoteDeviceReducer,
    effectHandler: EditRemoteDeviceEffectHandler,
    viewStateMapper: EditRemoteDeviceViewStateMapper,
) : BaseStoreViewModel<EditRemoteDeviceViewState, EditRemoteDeviceState, EditRemoteDeviceCommand, EditRemoteDeviceSideEffect>(
    initialState = EditRemoteDeviceState(
        deviceId = savedStateHandle.get<Long>("device_id")?.takeIf { it != 0L },
        name = "",
        host = "",
        port = RemoteDevice.DEFAULT_ADB_PORT.toString(),
        isLoading = savedStateHandle.get<Long>("device_id") != null,
    ),
    reducer = reducer,
    effectHandlers = listOf(effectHandler),
    viewStateMapper = viewStateMapper,
    initialSideEffects = listOf(
        EditRemoteDeviceSideEffect.LoadDevice(
            savedStateHandle.get<Long>("device_id")?.takeIf { it != 0L }
        ),
    ),
)