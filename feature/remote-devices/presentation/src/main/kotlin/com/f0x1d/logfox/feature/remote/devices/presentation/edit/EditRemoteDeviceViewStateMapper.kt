package com.f0x1d.logfox.feature.remote.devices.presentation.edit

import com.f0x1d.logfox.core.tea.ViewStateMapper
import javax.inject.Inject

internal class EditRemoteDeviceViewStateMapper @Inject constructor() :
    ViewStateMapper<EditRemoteDeviceState, EditRemoteDeviceViewState> {

    override fun map(state: EditRemoteDeviceState) = EditRemoteDeviceViewState(
        name = state.name,
        host = state.host,
        port = state.port,
        isEditing = state.deviceId != null,
        isLoading = state.isLoading,
    )
}