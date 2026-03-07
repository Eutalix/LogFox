package com.f0x1d.logfox.feature.remote.devices.impl.di

import com.f0x1d.logfox.feature.remote.devices.api.data.RemoteDevicesRepository
import com.f0x1d.logfox.feature.remote.devices.api.domain.ConnectToDeviceUseCase
import com.f0x1d.logfox.feature.remote.devices.api.domain.DeleteRemoteDeviceUseCase
import com.f0x1d.logfox.feature.remote.devices.api.domain.DisconnectFromDeviceUseCase
import com.f0x1d.logfox.feature.remote.devices.api.domain.GetAllRemoteDevicesFlowUseCase
import com.f0x1d.logfox.feature.remote.devices.api.domain.GetConnectionStateFlowUseCase
import com.f0x1d.logfox.feature.remote.devices.api.domain.GetRemoteDeviceByIdUseCase
import com.f0x1d.logfox.feature.remote.devices.api.domain.SaveRemoteDeviceUseCase
import com.f0x1d.logfox.feature.remote.devices.impl.data.RemoteDevicesRepositoryImpl
import com.f0x1d.logfox.feature.remote.devices.impl.domain.ConnectToDeviceUseCaseImpl
import com.f0x1d.logfox.feature.remote.devices.impl.domain.DeleteRemoteDeviceUseCaseImpl
import com.f0x1d.logfox.feature.remote.devices.impl.domain.DisconnectFromDeviceUseCaseImpl
import com.f0x1d.logfox.feature.remote.devices.impl.domain.GetAllRemoteDevicesFlowUseCaseImpl
import com.f0x1d.logfox.feature.remote.devices.impl.domain.GetConnectionStateFlowUseCaseImpl
import com.f0x1d.logfox.feature.remote.devices.impl.domain.GetRemoteDeviceByIdUseCaseImpl
import com.f0x1d.logfox.feature.remote.devices.impl.domain.SaveRemoteDeviceUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface RemoteDevicesModule {

    @Binds
    @Singleton
    fun bindRemoteDevicesRepository(impl: RemoteDevicesRepositoryImpl): RemoteDevicesRepository

    @Binds
    fun bindGetAllRemoteDevicesFlowUseCase(
        impl: GetAllRemoteDevicesFlowUseCaseImpl,
    ): GetAllRemoteDevicesFlowUseCase

    @Binds
    fun bindGetConnectionStateFlowUseCase(
        impl: GetConnectionStateFlowUseCaseImpl,
    ): GetConnectionStateFlowUseCase

    @Binds
    fun bindConnectToDeviceUseCase(impl: ConnectToDeviceUseCaseImpl): ConnectToDeviceUseCase

    @Binds
    fun bindDisconnectFromDeviceUseCase(
        impl: DisconnectFromDeviceUseCaseImpl,
    ): DisconnectFromDeviceUseCase

    @Binds
    fun bindSaveRemoteDeviceUseCase(impl: SaveRemoteDeviceUseCaseImpl): SaveRemoteDeviceUseCase

    @Binds
    fun bindDeleteRemoteDeviceUseCase(impl: DeleteRemoteDeviceUseCaseImpl): DeleteRemoteDeviceUseCase

    @Binds
    fun bindGetRemoteDeviceByIdUseCase(
        impl: GetRemoteDeviceByIdUseCaseImpl,
    ): GetRemoteDeviceByIdUseCase
}