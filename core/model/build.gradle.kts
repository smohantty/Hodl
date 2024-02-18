plugins {
    id("hodl.android.library")
    id("hodl.android.hilt")
}

android {
    namespace = "com.finance.hodl.model"
}

dependencies {
    api(libs.core.ktx)
}