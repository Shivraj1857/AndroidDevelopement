plugins {
    alias(libs.plugins.android.application)
    id("com.google.devtools.ksp")
}

android {
    namespace = "io.mastercoding.contactmanagerapp"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "io.mastercoding.contactmanagerapp"
        minSdk = 24
        targetSdk = 36
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
    buildFeatures {
        dataBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //room db
    val room_version = "2.8.4"
    val lifecycle_version = "2.10.0"
    implementation("androidx.room:room-runtime:$room_version")
    //kotlin symbol processing(KSP)
    ksp("androidx.room:room-compiler:$room_version")

    //room with coroutine
    implementation("androidx.room:room-ktx:${room_version}")

    //coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel:${lifecycle_version}")
// LiveData
//    implementation("androidx.lifecycle:lifecycle-livedata:${lifecycle_version}")
}