package com.f0x1d.logfox.feature.remote.devices.presentation.edit.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.f0x1d.logfox.core.tea.BaseStoreFragment
import com.f0x1d.logfox.core.ui.view.setClickListenerOn
import com.f0x1d.logfox.core.ui.view.setupBackButtonForNavController
import com.f0x1d.logfox.feature.remote.devices.presentation.R
import com.f0x1d.logfox.feature.remote.devices.presentation.databinding.FragmentEditRemoteDeviceBinding
import com.f0x1d.logfox.feature.remote.devices.presentation.edit.EditRemoteDeviceCommand
import com.f0x1d.logfox.feature.remote.devices.presentation.edit.EditRemoteDeviceSideEffect
import com.f0x1d.logfox.feature.remote.devices.presentation.edit.EditRemoteDeviceState
import com.f0x1d.logfox.feature.remote.devices.presentation.edit.EditRemoteDeviceViewModel
import com.f0x1d.logfox.feature.remote.devices.presentation.edit.EditRemoteDeviceViewState
import com.f0x1d.logfox.feature.strings.Strings
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.applyInsetter

@AndroidEntryPoint
internal class EditRemoteDeviceFragment :
    BaseStoreFragment<
        FragmentEditRemoteDeviceBinding,
        EditRemoteDeviceViewState,
        EditRemoteDeviceState,
        EditRemoteDeviceCommand,
        EditRemoteDeviceSideEffect,
        EditRemoteDeviceViewModel,
        >() {

    override val viewModel by viewModels<EditRemoteDeviceViewModel>()

    private var ignoreTextChanges = false

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentEditRemoteDeviceBinding.inflate(inflater, container, false)

    override fun FragmentEditRemoteDeviceBinding.onViewCreated(view: View, savedInstanceState: Bundle?) {
        scrollView.applyInsetter {
            type(navigationBars = true) {
                padding(vertical = true)
            }
        }

        toolbar.setupBackButtonForNavController()
        toolbar.menu.setClickListenerOn(R.id.save_item) {
            send(EditRemoteDeviceCommand.Save)
        }

        nameText.doAfterTextChanged { text ->
            if (!ignoreTextChanges) {
                send(EditRemoteDeviceCommand.UpdateName(text?.toString() ?: ""))
            }
        }
        hostText.doAfterTextChanged { text ->
            if (!ignoreTextChanges) {
                send(EditRemoteDeviceCommand.UpdateHost(text?.toString() ?: ""))
            }
        }
        portText.doAfterTextChanged { text ->
            if (!ignoreTextChanges) {
                send(EditRemoteDeviceCommand.UpdatePort(text?.toString() ?: ""))
            }
        }
    }

    override fun render(state: EditRemoteDeviceViewState) {
        binding.toolbar.setTitle(
            if (state.isEditing) Strings.edit_device else Strings.add_device
        )

        binding.loadingProgress.isVisible = state.isLoading
        binding.contentContainer.isVisible = !state.isLoading

        ignoreTextChanges = true
        if (binding.nameText.text?.toString() != state.name) {
            binding.nameText.setText(state.name)
        }
        if (binding.hostText.text?.toString() != state.host) {
            binding.hostText.setText(state.host)
        }
        if (binding.portText.text?.toString() != state.port) {
            binding.portText.setText(state.port)
        }
        ignoreTextChanges = false
    }

    override fun handleSideEffect(sideEffect: EditRemoteDeviceSideEffect) {
        when (sideEffect) {
            is EditRemoteDeviceSideEffect.NavigateBack -> {
                findNavController().popBackStack()
            }

            is EditRemoteDeviceSideEffect.ShowError -> {
                Snackbar.make(binding.root, sideEffect.message, Snackbar.LENGTH_LONG).show()
            }

            // Business logic side effects - handled by EffectHandler
            else -> Unit
        }
    }
}