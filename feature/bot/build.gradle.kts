plugins {
    id("hodl.android.feature")
}

android {
    namespace = "com.finance.hodl.feature.bot"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:data"))
}