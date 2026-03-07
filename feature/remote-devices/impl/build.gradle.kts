plugins {
    alias(libs.plugins.logfox.android.feature)
}

android.namespace = "com.f0x1d.logfox.feature.remote.devices.impl"

dependencies {
    api(projects.feature.remoteDevices.api)

    implementation(projects.feature.database.api)
    implementation(projects.feature.terminals.api)
    implementation(projects.core.di)

    implementation(libs.dadb)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.timber)
}