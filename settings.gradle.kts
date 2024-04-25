pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    @Suppress("UnstableApiUsage") repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    @Suppress("UnstableApiUsage") repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Coffee"
include(":app")
include(":core:timeout")
include(":core:result")
include(":core:navigation")
include(":feature:auth")
include(":feature:compliment")
include(":data:auth")
include(":data:auth_token")
include(":data:compliment")
include(":core:network")
