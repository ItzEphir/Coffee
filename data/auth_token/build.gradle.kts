plugins {
    `java-library`
    alias(libs.plugins.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin{
    jvmToolchain(17)
}

dependencies{
    implementation(libs.coroutines)
    implementation(libs.koin.core)
    implementation(libs.datastore.core)
}