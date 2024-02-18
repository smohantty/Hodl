pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "hodl"
include(":app")
include(":core:model")
include(":core:data")
include(":core:domain")
include(":core:designsystem")
include(":core:database")
include(":core:exchange")

include(":feature:bot")


