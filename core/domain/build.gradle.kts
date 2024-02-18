plugins {
    id("hodl.android.library")
    id("hodl.android.hilt")
}

android {
    namespace = "com.finance.hodl.domain"
}

dependencies {
    api(libs.core.ktx)
    implementation(project(":core:data"))
    implementation(project(":core:model"))

}