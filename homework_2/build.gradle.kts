plugins {
    //alias(libs.plugins.application)
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("org.jetbrains.kotlin.plugin.serialization") version "2.0.0-Beta2"
    id ("kotlin-kapt")
}
android {
    namespace = "com.example.homework_2"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.homework_2"
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
    viewBinding {
        enable = true
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.cicerone)
    implementation (libs.shimmer)
    implementation (libs.androidx.lifecycle.viewmodel.ktx)

    implementation (libs.retrofit)
    implementation (libs.retrofit2.kotlin.coroutines.adapter)
    implementation (libs.converter.gson)
    implementation (libs.retrofit2.kotlinx.serialization.converter)

    implementation (libs.okhttp)
    implementation (libs.logging.interceptor)
    implementation (libs.glide)

    implementation (libs.kotlinx.serialization.json)
    implementation (libs.kotlinx.coroutines.android)

    implementation (libs.elmslie.core)
    implementation (libs.elmslie.android)

    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.android.v171)

    implementation (libs.dagger)
    kapt(libs.dagger.compiler)

    implementation (libs.androidx.room.runtime)
    kapt (libs.androidx.room.compiler)
    implementation (libs.androidx.room.ktx)

    implementation (libs.androidx.paging.runtime.ktx)

}