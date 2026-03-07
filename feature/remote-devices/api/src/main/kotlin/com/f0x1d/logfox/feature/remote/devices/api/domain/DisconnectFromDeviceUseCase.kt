package com.f0x1d.logfox.feature.remote.devices.api.domain

interface DisconnectFromDeviceUseCase {
    suspend operator fun invoke()
}