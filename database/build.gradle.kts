plugins {
    id("com.android.library")
    id("kotlin-kapt")
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.deyber.database"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        targetSdk = 34
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
}

dependencies {

    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation(libs.room)
    implementation(libs.android.dagger.hilt)

    kapt(libs.android.dagger.kpt)
    implementation(libs.androidx.room.rxjava2)

}