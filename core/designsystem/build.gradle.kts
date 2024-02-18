plugins {
    id("hodl.android.library")
    id("hodl.android.library.compose")
}

android {
    namespace = "com.finance.hodl.designsystem"
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    api(libs.core.ktx)
    api(libs.bundles.compose)
}