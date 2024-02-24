// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.serialization) apply false
    alias(libs.plugins.parcelize) apply false
    alias(libs.plugins.realm) apply false
    alias(libs.plugins.google.services) apply false
}
true // Needed to make the Suppress annotation work for the plugins block