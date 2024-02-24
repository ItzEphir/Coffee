import org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("java-library")
    alias(libs.plugins.kotlin.jvm)
}

kotlin{
    compilerOptions{
        jvmTarget.set(JVM_1_8)
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    
    implementation(libs.kotlinx.coroutines.core)
}