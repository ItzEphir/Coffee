plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android)
}

android {
    namespace = "com.ephirium.coffee.preview"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(libs.ui.tooling)
    implementation(libs.ui.tooling.preview)
}