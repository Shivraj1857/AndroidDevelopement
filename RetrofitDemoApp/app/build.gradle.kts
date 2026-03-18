plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "io.mastercoding.retrofitdemoapp"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "io.mastercoding.retrofitdemoapp"
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

    //retrofit chi
    implementation(libs.retrofit)
    //gson
    implementation(libs.converter.gson)
    //retro json
    implementation(libs.retrofit.converter.gson)


    //live data
    implementation(libs.androidx.lifecycle.livedata.ktx)

    //coro
    implementation(libs.kotlinx.coroutines.android)

}