package com.f0x1d.logfox.feature.database.impl.di

import com.f0x1d.logfox.feature.database.api.data.AppCrashDataSource
import com.f0x1d.logfox.feature.database.api.data.DisabledAppDataSource
import com.f0x1d.logfox.feature.database.api.data.LogRecordingDataSource
import com.f0x1d.logfox.feature.database.api.data.RemoteDeviceDataSource
import com.f0x1d.logfox.feature.database.api.data.UserFilterDataSource
import com.f0x1d.logfox.feature.database.impl.data.AppCrashDataSourceImpl
import com.f0x1d.logfox.feature.database.impl.data.DisabledAppDataSourceImpl
import com.f0x1d.logfox.feature.database.impl.data.LogRecordingDataSourceImpl
import com.f0x1d.logfox.feature.database.impl.data.RemoteDeviceDataSourceImpl
import com.f0x1d.logfox.feature.database.impl.data.UserFilterDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataSourcesModule {

    @Binds
    fun bindUserFilterDataSource(impl: UserFilterDataSourceImpl): UserFilterDataSource

    @Binds
    fun bindAppCrashDataSource(impl: AppCrashDataSourceImpl): AppCrashDataSource

    @Binds
    fun bindLogRecordingDataSource(impl: LogRecordingDataSourceImpl): LogRecordingDataSource

    @Binds
    fun bindDisabledAppDataSource(impl: DisabledAppDataSourceImpl): DisabledAppDataSource

    @Binds
    fun bindRemoteDeviceDataSource(impl: RemoteDeviceDataSourceImpl): RemoteDeviceDataSource
}