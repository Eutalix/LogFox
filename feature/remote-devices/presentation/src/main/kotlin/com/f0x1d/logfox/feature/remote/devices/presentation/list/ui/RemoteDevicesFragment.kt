package com.f0x1d.logfox.feature.remote.devices.presentation.list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.f0x1d.logfox.core.context.isHorizontalOrientation
import com.f0x1d.logfox.core.tea.BaseStoreFragment
import com.f0x1d.logfox.feature.navigation.api.R as NavigationR
import com.f0x1d.logfox.feature.remote.devices.api.model.RemoteDevice
import com.f0x1d.logfox.feature.remote.devices.presentation.databinding.FragmentRemoteDevicesBinding
import com.f0x1d.logfox.feature.remote.devices.presentation.list.RemoteDevicesCommand
import com.f0x1d.logfox.feature.remote.devices.presentation.list.RemoteDevicesSideEffect
import com.f0x1d.logfox.feature.remote.devices.presentation.list.RemoteDevicesState
import com.f0x1d.logfox.feature.remote.devices.presentation.list.RemoteDevicesViewModel
import com.f0x1d.logfox.feature.remote.devices.presentation.list.RemoteDevicesViewState
import com.f0x1d.logfox.feature.remote.devices.presentation.list.adapter.RemoteDevicesAdapter
import com.f0x1d.logfox.feature.remote.devices.presentation.list.model.RemoteDeviceItem
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.applyInsetter

@AndroidEntryPoint
internal class RemoteDevicesFragment :
    BaseStoreFragment<
        FragmentRemoteDevicesBinding,
        RemoteDevicesViewState,
        RemoteDevicesState,
        RemoteDevicesCommand,
        RemoteDevicesSideEffect,
        RemoteDevicesViewModel,
        >() {

    override val viewModel by viewModels<RemoteDevicesViewModel>()

    private val adapter by lazy {
        RemoteDevicesAdapter(
            onConnectClick = { item ->
                send(RemoteDevicesCommand.ConnectToDevice(item.toDevice()))
            },
            onDisconnectClick = {
                send(RemoteDevicesCommand.Disconnect)
            },
            onEditClick = { item ->
                send(RemoteDevicesCommand.EditDeviceClicked(item.toDevice()))
            },
            onDeleteClick = { item ->
                send(RemoteDevicesCommand.DeleteDevice(item.toDevice()))
            },
        )
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentRemoteDevicesBinding.inflate(inflater, container, false)

    override fun FragmentRemoteDevicesBinding.onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireContext().isHorizontalOrientation.also { horizontalOrientation ->
            devicesRecycler.applyInsetter {
                type(navigationBars = true) {
                    padding(vertical = horizontalOrientation)
                }
            }
            addFab.applyInsetter {
                type(navigationBars = true) {
                    margin(vertical = horizontalOrientation)
                }
            }
        }

        devicesRecycler.layoutManager = LinearLayoutManager(requireContext())
        devicesRecycler.adapter = adapter

        addFab.setOnClickListener {
            send(RemoteDevicesCommand.AddDeviceClicked)
        }

        startLoggingButton.setOnClickListener {
            send(RemoteDevicesCommand.StartLoggingClicked)
        }
    }

    override fun render(state: RemoteDevicesViewState) {
        adapter.submitList(state.devices)

        binding.placeholderLayout.root.isVisible = state.devices.isEmpty()
        binding.connectingProgress.isVisible = state.isConnecting
        binding.startLoggingButton.isVisible = state.isConnected
    }

    override fun handleSideEffect(sideEffect: RemoteDevicesSideEffect) {
        when (sideEffect) {
            is RemoteDevicesSideEffect.NavigateToAddDevice -> {
                findNavController().navigate(NavigationR.id.action_remoteDevicesFragment_to_editRemoteDeviceFragment)
            }

            is RemoteDevicesSideEffect.NavigateToEditDevice -> {
                findNavController().navigate(
                    resId = NavigationR.id.action_remoteDevicesFragment_to_editRemoteDeviceFragment,
                    args = bundleOf("device_id" to sideEffect.deviceId),
                )
            }

            is RemoteDevicesSideEffect.NavigateToLogs -> {
                findNavController().popBackStack()
            }

            is RemoteDevicesSideEffect.ShowError -> {
                Snackbar.make(binding.root, sideEffect.message, Snackbar.LENGTH_LONG).show()
            }

            // Business logic side effects - handled by EffectHandler
            else -> Unit
        }
    }

    private fun RemoteDeviceItem.toDevice() = RemoteDevice(
        id = deviceId,
        name = name,
        host = address.substringBefore(":"),
        port = address.substringAfter(":").toIntOrNull() ?: RemoteDevice.DEFAULT_ADB_PORT,
    )
}