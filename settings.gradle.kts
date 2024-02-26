@file:Suppress("UnstableApiUsage")

include(":auth")



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
include(":databases:compliments")
include(":databases:users")
include(":databases:compliment_id")
include(":data")
include(":domain")
include(":common")
 