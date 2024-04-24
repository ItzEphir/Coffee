plugins {
    alias(libs.plugins.application)
    alias(libs.plugins.android)
    alias(libs.plugins.serialization)
    alias(libs.plugins.parcelize)
}

android {
    namespace = "com.ephirium.coffee.app"
    compileSdk = 34
    
    defaultConfig {
        applicationId = "com.ephirium.coffee.app"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        
        buildConfigField("String", "SERVER_URL", properties["server.url"].toString())
        buildConfigField("String", "SERVER_PORT", properties["server.port"].toString())
        
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.12"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    
    implementation(project(":common"))
    implementation(project(":feature:auth"))
    implementation(project(":data:auth"))
    
    implementation(libs.bundles.android)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.koin.android)
    implementation(libs.serialization.json)
    implementation(libs.datastore.preferences)
}