package com.f0x1d.logfox.feature.remote.devices.presentation.list.model

import com.f0x1d.logfox.feature.remote.devices.api.model.RemoteDevice

fun RemoteDevice.toItem(isConnected: Boolean) = RemoteDeviceItem(
    deviceId = id,
    name = name,
    address = address,
    isConnected = isConnected,
)