import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.androidx.baselineprofile)
}

android {
    namespace = "com.khun.smartstream"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.khun.smartstream"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

    }

    val secretProperties: File = rootProject.file("secrets.properties")
    val sitProps = Properties()
    secretProperties.inputStream().use {
        sitProps.load(it)
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", sitProps.getProperty("BASE_URL_DEBUG"))
            buildConfigField("String", "API_KEY", sitProps.getProperty("API_KEY_DEBUG"))
            buildConfigField(
                "String",
                "YOUTUBE_API_KEY",
                sitProps.getProperty("YOUTUBE_API_KEY_DEBUG")
            )
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", sitProps.getProperty("BASE_URL"))
            buildConfigField("String", "API_KEY", sitProps.getProperty("API_KEY"))
            buildConfigField("String", "YOUTUBE_API_KEY", sitProps.getProperty("YOUTUBE_API_KEY"))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.tv.foundation)
    implementation(libs.androidx.tv.material)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Material
    implementation(libs.androidx.material)

    // extra material icons
    implementation(libs.androidx.material.icons.extended)

    // Material components optimized for TV apps
    implementation(libs.androidx.tv.material)

    // Compose Navigation
    implementation(libs.androidx.navigation.compose)

    // Coil
    implementation(libs.coil.compose)

    // JSON parser
    implementation(libs.kotlinx.serialization)
    implementation(libs.retrofit2.kotlinx.serialization.converter)

    // Media3
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.ui)

    // SplashScreen
    implementation(libs.androidx.core.splashscreen)

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.animation)
    ksp(libs.hilt.compiler)

    // Baseline profile installer
    implementation(libs.androidx.profileinstaller)

    //Retrofit and Interceptor
    implementation(libs.retrofit)
    implementation(libs.logging.interceptor)

    //implementation (libs.youtube.api)
//    implementation(libs.google.api.services.youtube)
//    implementation(libs.google.api.client.android)
    implementation(libs.core)
}