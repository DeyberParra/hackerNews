plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.gradle.detekt)
    id("kotlin-kapt")
    id("kotlin-parcelize")
    alias(libs.plugins.android.hilt)

}

android {
    namespace = "com.deyber.hackernews"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.deyber.hackernews"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

}

dependencies {

    implementation(project(":database"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    implementation(libs.swipe.recyclerview)
    implementation(libs.swiperefresh)

    implementation(libs.android.dagger.hilt)
    kapt(libs.android.dagger.kpt)
    implementation(libs.androidx.dagger.hilt.navigation.fragment)

    implementation(libs.retrofit)
    implementation(libs.retrofit.rxjava2)
    implementation(libs.retrofit.converter.gson)

    testImplementation(libs.junit)
    testImplementation (libs.mockk)
    testImplementation(libs.coroutine.test)






}