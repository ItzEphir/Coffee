plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android)
}

android {
    namespace = "com.ephirium.coffee.commonAndroid"
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
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.12"
    }
}

dependencies {
    
    implementation(libs.bundles.android)
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
}