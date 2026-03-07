package com.f0x1d.logfox.feature.remote.devices.presentation.list.viewholder

import androidx.core.view.isVisible
import com.f0x1d.logfox.core.recycler.viewholder.BaseViewHolder
import com.f0x1d.logfox.feature.remote.devices.presentation.databinding.ItemRemoteDeviceBinding
import com.f0x1d.logfox.feature.remote.devices.presentation.list.model.RemoteDeviceItem
import com.f0x1d.logfox.feature.strings.Strings

internal class RemoteDeviceViewHolder(
    binding: ItemRemoteDeviceBinding,
    private val onConnectClick: (RemoteDeviceItem) -> Unit,
    private val onDisconnectClick: (RemoteDeviceItem) -> Unit,
    private val onEditClick: (RemoteDeviceItem) -> Unit,
    private val onDeleteClick: (RemoteDeviceItem) -> Unit,
) : BaseViewHolder<RemoteDeviceItem, ItemRemoteDeviceBinding>(binding) {

    init {
        binding.apply {
            connectButton.setOnClickListener {
                val item = currentItem ?: return@setOnClickListener
                if (item.isConnected) {
                    onDisconnectClick(item)
                } else {
                    onConnectClick(item)
                }
            }
            editButton.setOnClickListener {
                val item = currentItem ?: return@setOnClickListener
                onEditClick(item)
            }
            deleteButton.setOnClickListener {
                val item = currentItem ?: return@setOnClickListener
                onDeleteClick(item)
            }
        }
    }

    override fun ItemRemoteDeviceBinding.bindTo(data: RemoteDeviceItem) {
        nameText.text = data.name
        addressText.text = data.address

        connectedIndicator.isVisible = data.isConnected
        connectButton.setText(
            if (data.isConnected) Strings.disconnect else Strings.connect
        )
    }
}