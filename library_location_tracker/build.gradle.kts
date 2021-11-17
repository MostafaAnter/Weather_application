plugins {
    id ("com.android.library")
    id ("kotlin-android")

    // fot dagger until now
    kotlin("kapt")
    //for hilt
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = AppConfig.compileSdk
    buildToolsVersion = AppConfig.buildToolsVersion

    defaultConfig {
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isMinifyEnabled = true
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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

    //depend on core module for string res
    implementation(project(":core"))

    implementation (AppDependencies.kotlin_stdlib)
    implementation (AppDependencies.core_ktx)
    implementation (AppDependencies.androidx_appcompat)

    // region dagger
    implementation (AppDependencies.dagger_hilt)
    kapt (AppDependencies.dagger_kapt)
    // end region

    // region Coroutine
    implementation (AppDependencies.coroutine_android)
    implementation (AppDependencies.coroutine_core)
    //endregion

    // play service location for enable gps from inside app
    implementation (AppDependencies.play_service_location)

    testImplementation (AppDependencies.junit)
    androidTestImplementation (AppDependencies.junit_test)
    androidTestImplementation (AppDependencies.espresso_core)
}