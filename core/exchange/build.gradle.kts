plugins {
    id("hodl.android.library")
    id("hodl.android.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "com.finance.hodl.mcf"
}

dependencies {
    api(libs.core.ktx)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.bundles.network)
}