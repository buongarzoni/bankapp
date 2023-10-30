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
include(":components")
include(":onboarding:feature")
include(":onboarding:model")
include(":home:feature")
include(":home:model")
include(":infrastructure:auth:firebase")
include(":infrastructure:database:firebase")
