plugins {
    `java-library`
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.serialization)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin{
    jvmToolchain(17)
}

dependencies{
    implementation(project(":core:result"))
    implementation(project(":core:network"))
    implementation(project(":data:auth_token"))
    
    implementation(libs.coroutines)
    implementation(libs.serialization.json)
    implementation(libs.bundles.ktor)
    implementation(libs.koin.core)
}