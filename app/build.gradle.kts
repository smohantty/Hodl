plugins {
    id("hodl.android.application")
    id("hodl.android.application.compose")
    id("hodl.android.hilt")
    id("hodl.android.room")
    id("hodl.android.application.flavors")
}

android {
    namespace = "com.finance.hodl"
    defaultConfig {
        applicationId = "com.finance.hodl"
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}

dependencies {
    implementation(project(":feature:bot"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:data"))


    implementation(libs.core.ktx)
    implementation(libs.lifecycler)
    implementation(libs.activity)

    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.compose.navigation)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(project(":feature:bot"))
}