plugins {
    id ("com.android.library")
    id ("kotlin-android")

    // fot dagger until now
    id("kotlin-kapt")
    //for hilt
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = AppConfig.compileSdk
    buildToolsVersion = AppConfig.buildToolsVersion

    defaultConfig {
        minSdk = AppConfig.minSdk
        targetSdk =  AppConfig.targetSdk

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

    // enable data binding inside module
    dataBinding.apply {
        @Suppress("DEPRECATION")
        isEnabled = true
    }
}

dependencies {
    //depend on core module for string res
    implementation(project(":core"))
    implementation(project(":library_location_tracker"))

    implementation (AppDependencies.kotlin_stdlib)
    implementation (AppDependencies.core_ktx)
    implementation (AppDependencies.androidx_appcompat)
    implementation (AppDependencies.google_material)

    // constraint layout
    implementation (AppDependencies.legacy_support)
    implementation (AppDependencies.constraint_layout)

    //navigation
    implementation (AppDependencies.navigation_fragment)
    implementation (AppDependencies.navigation_ui)

    // region dagger
    implementation (AppDependencies.dagger_hilt)
    kapt (AppDependencies.dagger_kapt)
    // end region

    //lifecycle scope
    implementation(AppDependencies.scopes_lifecycle_coroutines)
    implementation(AppDependencies.scopes_viewModel_coroutines)

    // region dagger
    implementation (AppDependencies.dagger_hilt)
    kapt (AppDependencies.dagger_kapt)
    // end region

    //region viewmodel lifecycle
    implementation(AppDependencies.hilt_viewModel_lifecycle)
    kapt(AppDependencies.hilt_viewModel_lifecycle_kapt)
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

    //region permission dispatcher
    implementation (AppDependencies.permission_dispatcher)
    kapt (AppDependencies.permission_dispatcher_kapt)
    //endregion

    // coil image loader
    implementation(AppDependencies.coil)

    //region database
    implementation(AppDependencies.room_database)
    implementation(AppDependencies.room_database_ktx)
    kapt(AppDependencies.room_database_kapt)
    //endregion

    // paging lib
    implementation(AppDependencies.paging_lib)


    //region unit test and ui test
    //test with jvm
    testImplementation (AppDependencies.junit)
    testImplementation (AppDependencies.truth_test)

    //test with android device
    androidTestImplementation (AppDependencies.junit)
    androidTestImplementation (AppDependencies.truth_test)
    androidTestImplementation (AppDependencies.junit_test)
    androidTestImplementation (AppDependencies.test_coroutine)
    //for support app context inside test method
    androidTestImplementation (AppDependencies.test_arch_core)
    androidTestImplementation (AppDependencies.espresso_core)
    //endregion
}