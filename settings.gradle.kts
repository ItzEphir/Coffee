@file:Suppress("UnstableApiUsage")

include(":feature:compliment")


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

rootProject.name = "Coffee"
include(":app")
include(":common")
include(":feature:auth")
include(":data:auth")
include(":data:auth_token")
 