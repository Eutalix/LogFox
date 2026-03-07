package com.f0x1d.logfox.feature.remote.devices.presentation.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.f0x1d.logfox.core.recycler.adapter.BaseListAdapter
import com.f0x1d.logfox.core.recycler.diffCallback
import com.f0x1d.logfox.feature.remote.devices.presentation.databinding.ItemRemoteDeviceBinding
import com.f0x1d.logfox.feature.remote.devices.presentation.list.model.RemoteDeviceItem
import com.f0x1d.logfox.feature.remote.devices.presentation.list.viewholder.RemoteDeviceViewHolder

internal class RemoteDevicesAdapter(
    private val onConnectClick: (RemoteDeviceItem) -> Unit,
    private val onDisconnectClick: (RemoteDeviceItem) -> Unit,
    private val onEditClick: (RemoteDeviceItem) -> Unit,
    private val onDeleteClick: (RemoteDeviceItem) -> Unit,
) : BaseListAdapter<RemoteDeviceItem, ItemRemoteDeviceBinding>(diffCallback<RemoteDeviceItem>()) {

    override fun createHolder(layoutInflater: LayoutInflater, parent: ViewGroup) = RemoteDeviceViewHolder(
        binding = ItemRemoteDeviceBinding.inflate(layoutInflater, parent, false),
        onConnectClick = onConnectClick,
        onDisconnectClick = onDisconnectClick,
        onEditClick = onEditClick,
        onDeleteClick = onDeleteClick,
    )
}