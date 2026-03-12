plugins {
    alias(libs.plugins.android.application)
    id("com.google.devtools.ksp")
    id("androidx.navigation.safeargs")
    id("kotlin-parcelize")

}

android {
    namespace = "io.mastercoding.notetakingapp"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "io.mastercoding.notetakingapp"
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
    buildFeatures {
        dataBinding=true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.fragment)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    val nav_version = "2.9.7"
    // Views/Fragments integration
    implementation("androidx.navigation:navigation-fragment:${nav_version}")
    implementation("androidx.navigation:navigation-ui:${nav_version}")


    val lifecycle_version = "2.10.0"
    val arch_version = "2.2.0"
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${lifecycle_version}")
    // Annotation processor
    ksp("androidx.lifecycle:lifecycle-compiler:$lifecycle_version")


}