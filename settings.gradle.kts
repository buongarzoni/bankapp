pluginManagement {
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

rootProject.name = "bankapp"
include(":app")
include(":core")
include(":onboarding:feature")
include(":onboarding:model")
include(":components")
include(":feature:home")
include(":infrastructure:auth:firebase")
