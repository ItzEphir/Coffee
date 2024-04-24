plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android)
    alias(libs.plugins.parcelize)
}

android {
    namespace = "com.ephirium.coffee.feature.auth"
    compileSdk = 34
    
    defaultConfig {
        minSdk = 29
        
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions{
        jvmTarget = "17"
    }
    buildFeatures {
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
    implementation(project(":data:auth_token"))
    implementation(project(":data:auth"))
    
    implementation(libs.android.core)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    debugImplementation(libs.bundles.compose)
    implementation(libs.bundles.koin.android)
    implementation(libs.serialization.json)
    implementation(libs.navigation.compose)
    implementation(libs.coroutines)
}