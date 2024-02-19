plugins {
    id("hodl.android.feature")
    id("hodl.android.library.compose")
}

android {
    namespace = "com.finance.hodl.feature.bot"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation(project(":core:model"))
}