plugins {
    id("hodl.android.library")
    id("hodl.android.hilt")
    id("hodl.android.room")
}

android {
    namespace = "com.finance.hodl.database"
}

dependencies {
    api(libs.core.ktx)
    implementation(project(":core:model"))
}