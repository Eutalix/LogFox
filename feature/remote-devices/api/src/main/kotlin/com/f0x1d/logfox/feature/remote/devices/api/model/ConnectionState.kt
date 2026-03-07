package com.f0x1d.logfox.feature.remote.devices.api.model

sealed interface ConnectionState {
    data object Disconnected : ConnectionState
    data object Connecting : ConnectionState
    data class Connected(val device: RemoteDevice) : ConnectionState
    data class Error(val message: String) : ConnectionState
}