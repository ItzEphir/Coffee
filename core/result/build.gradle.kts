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

dependencies {
    implementation(libs.serialization.json)
    implementation(libs.coroutines)
    implementation(libs.ktor.client.core)
}