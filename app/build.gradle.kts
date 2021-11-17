plugins {
    id ("com.android.application")
    id ("kotlin-android")

    //for data binding and dagger and more
    id("kotlin-kapt")

    //for hilt
    id("dagger.hilt.android.plugin")

    //for google services
    id("com.google.gms.google-services")

    //for crashlytics
    id("com.google.firebase.crashlytics")
}

// to apply detekt code analysis on app module
apply { from(rootProject.file("detekt.gradle")) }

android {
    signingConfigs {
        create("config") {
            storeFile = file("../Untitled")
            storePassword = "password"
            keyAlias = "key0"
            keyPassword = "password"
        }
    }
    compileSdk = AppConfig.compileSdk
    buildToolsVersion = AppConfig.buildToolsVersion

    defaultConfig {
        applicationId = "app.anter.weatherapplication"
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
        // region multi dex support
        multiDexEnabled = true
        // endregion
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        signingConfig = signingConfigs.getByName("config")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("config")
        }
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("config")
        }

    }
    //support data binding
    buildFeatures{
        dataBinding = true
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
    // region enable multi dex
    implementation ("com.android.support:multidex:1.0.3")
    //endregion

    //for hilt app must depend on all modules
    //depend on features modules
    implementation(project(":core"))
    implementation(project(":feature_search_current_weather"))
    implementation(project(":library_location_tracker"))


    //region lib
    implementation (fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation (AppDependencies.kotlin_stdlib)
    implementation (AppDependencies.core_ktx)
    implementation (AppDependencies.androidx_appcompat)
    implementation (AppDependencies.google_material)
    implementation (AppDependencies.constraint_layout)
    implementation (AppDependencies.navigation_fragment)
    implementation (AppDependencies.navigation_ui)
    //endregion

    // region dagger
    implementation (AppDependencies.dagger_hilt)
    kapt (AppDependencies.dagger_kapt)
    // end region

    //region firebase
    implementation(platform(AppDependencies.firebase_bom))
    implementation (AppDependencies.firebase_analytics)
    implementation (AppDependencies.firebase_crashlytics)
    implementation (AppDependencies.firebase_messaging)
    //endregion

    //region event bus
    implementation (AppDependencies.event_bus)
    //endregion

    //region database
    implementation(AppDependencies.room_database)
    implementation(AppDependencies.room_database_ktx)
    kapt(AppDependencies.room_database_kapt)
    //endregion

    //region leak canary
    debugImplementation(AppDependencies.leak_canary)
    //endregion

    // region retrofit network calls
    implementation (AppDependencies.retrofit)
    implementation (AppDependencies.retrofit_converter)
    // for support android 4
    implementation (AppDependencies.okHttp_interceptor)
    implementation (AppDependencies.okHttp){
        version{
            strictly(Versions.okHttpVersion)
        }
    }
    // endregion

    // region Moshi very important to generate adapters converters
    implementation (AppDependencies.moshi)
    kapt(AppDependencies.moshi_kapt)
    // endregion

    // region Coroutine
    implementation (AppDependencies.coroutine_android)
    implementation (AppDependencies.coroutine_core)
    //endregion

    // coil image loader
    implementation(AppDependencies.coil)

    // paging lib
    implementation(AppDependencies.paging_lib)


    //region unit test and ui test
    // JUNIT
    testImplementation (AppDependencies.junit)
    androidTestImplementation (AppDependencies.junit_test)

    // ui test, instrumentation test
    // Espresso
    androidTestImplementation (AppDependencies.espresso_core)
    // androidx.test
    androidTestImplementation (AppDependencies.test_runner)
    androidTestImplementation (AppDependencies.test_rules)
    androidTestImplementation (AppDependencies.test_core)
    androidTestImplementation (AppDependencies.test_ext_junit)
    //endregion
}