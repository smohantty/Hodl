plugins {
    id("hodl.android.library")
    id("hodl.android.hilt")
}

android {
    namespace = "com.finance.hodl.data"
}

dependencies {
    api(libs.core.ktx)
    implementation(project(":core:database"))
    implementation(project(":core:exchange"))
    implementation(project(":core:model"))
}