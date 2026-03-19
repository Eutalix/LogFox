plugins {
    alias(libs.plugins.logfox.android.feature)
}

android {
    namespace = "com.f0x1d.logfox.feature.remote.devices.presentation"
    buildFeatures.viewBinding = true
}

dependencies {
    implementation(projects.feature.remoteDevices.api)
    implementation(projects.feature.navigation.api)
    implementation(projects.core.tea.base)
    implementation(projects.core.tea.android)
    implementation(projects.core.ui.base)
    implementation(projects.core.ui.icons)
    implementation(projects.core.ui.view)
    implementation(projects.core.di)
    implementation(projects.strings)
    implementation(projects.core.recycler)
    implementation(projects.core.context)

    implementation(libs.bundles.androidx)
    implementation(libs.bundles.androidx.navigation)
    implementation(libs.material)
    implementation(libs.insetter)
}