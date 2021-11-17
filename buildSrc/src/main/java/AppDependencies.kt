/**
 * Created by Mostafa Anter on 11/10/20.
 */
object AppDependencies {
    // project class paths
    const val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val tool_gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val tools_gradle_versions = "com.github.ben-manes:gradle-versions-plugin:${Versions.gradle_versions}"
    const val google_services = "com.google.gms:google-services:${Versions.google_services_version}"
    const val crashlytics = "com.google.firebase:firebase-crashlytics-gradle:${Versions.crashlytics_version}"

    //libs
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val core_ktx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val androidx_appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val google_material = "com.google.android.material:material:${Versions.material}"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation_fragment}"
    const val navigation_ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation_ui}"
    const val paging_lib = "androidx.paging:paging-runtime-ktx:${Versions.paging_version}"

    //testing dependencies
    const val junit = "junit:junit:${Versions.junit}"
    const val junit_test = "androidx.test.ext:junit:${Versions.extJunit}"
    const val truth_test = "com.google.truth:truth:${Versions.test_truth_version}"
    const val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val test_runner = "androidx.test:runner:${Versions.test_runner_version}"
    const val test_rules = "androidx.test:rules:${Versions.test_rules_version}"
    const val test_core = "androidx.test:core:${Versions.test_core_version}"
    const val test_arch_core = "androidx.arch.core:core-testing:${Versions.test_arch_core_version}"
    const val test_ext_junit = "androidx.test.ext:junit:${Versions.test_ext_version}"
    const val test_coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutine_test_version}"
    const val test_app_center_plugin = "com.microsoft.appcenter:espresso-test-extension:${Versions.app_center_test_plugin_version}"

    //dagger hilt for di
    const val dagger_class = "com.google.dagger:hilt-android-gradle-plugin:${Versions.dagger_hilt_version}"
    const val dagger_hilt = "com.google.dagger:hilt-android:${Versions.dagger_hilt_version}"
    const val dagger_kapt = "com.google.dagger:hilt-android-compiler:${Versions.dagger_hilt_version}"
    const val dagger_test = "com.google.dagger:hilt-android-testing:${Versions.dagger_hilt_version}"
    const val dagger_test_kapt = "com.google.dagger:hilt-android-compiler:${Versions.dagger_hilt_version}"

    // dagger hilt fo viewModel di
    const val hilt_viewModel_lifecycle = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.viewModel_hilt_version}"
    const val hilt_viewModel_lifecycle_kapt = "androidx.hilt:hilt-compiler:${Versions.viewModel_hilt_version}"

    //retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    const val retrofit_converter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit_version}"

    //okhttp
    const val okHttp_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpVersion}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttpVersion}"

    //coroutine
    const val coroutine_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_version}"
    const val coroutine_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_version}"

    //moshi converter
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi_version}"
    const val moshi_kapt =  "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi_version}"

    //legacy support
    const val legacy_support = "androidx.legacy:legacy-support-v4:${Versions.legacy_version}"

    //annotation
    const val annotation = "androidx.annotation:annotation:${Versions.annotation_version}"

    //viewpager
    const val viewpager = "androidx.viewpager2:viewpager2:${Versions.viewpager_version}"

    //card view
    const val card_view = "androidx.cardview:cardview:${Versions.card_view_version}"

    //coil
    const val coil = "io.coil-kt:coil:${Versions.coil_version}"

    //firebase
    const val firebase_bom = "com.google.firebase:firebase-bom:${Versions.firebase_bom_version}"
    const val firebase_analytics = "com.google.firebase:firebase-analytics-ktx"
    const val firebase_crashlytics = "com.google.firebase:firebase-crashlytics"
    const val firebase_messaging = "com.google.firebase:firebase-messaging-ktx"
    const val firebase_authentication = "com.google.firebase:firebase-auth-ktx"
    const val play_services_auth = "com.google.android.gms:play-services-auth:${Versions.firebase_play_services_auth_version}"

    //permission dispatcher
    const val permission_dispatcher = "org.permissionsdispatcher:permissionsdispatcher:${Versions.permission_dispatcher_version}"
    const val permission_dispatcher_kapt = "org.permissionsdispatcher:permissionsdispatcher-processor:${Versions.permission_dispatcher_version}"

    //play service location
    const val play_service_location = "com.google.android.gms:play-services-location:${Versions.play_service_location_version}"

    // ktx dependencies for lifecycleScope
    const val scopes_lifecycle_coroutines = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.scopes_version}"
    const val scopes_viewModel_coroutines = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.scopes_version}"

    // event bus
    const val event_bus = "org.greenrobot:eventbus:${Versions.event_bus_version}"

    // room database
    const val room_database = "androidx.room:room-runtime:${Versions.room_version}"
    const val room_database_kapt =  "androidx.room:room-compiler:${Versions.room_version}"
    const val room_database_ktx = "androidx.room:room-ktx:${Versions.room_version}"

    // leak canary to detect memory leak
    const val leak_canary = "com.squareup.leakcanary:leakcanary-android:${Versions.leak_canary_version}"

    // falcon screen shot to current screen
    const val falcon_screen_shot = "com.jraska:falcon:${Versions.falcon_version}"

    // stream chat sdk
    const val stream_chat_sdk = "io.getstream:stream-chat-android-ui-components:${Versions.stream_version}"

    // facebook shimmer
    const val facebook_shimmer = "com.facebook.shimmer:shimmer:${Versions.facebook_shimmer_version}"

    // observe events like onCreate and onStart inside fragments or activities
    const val observe_events = "androidx.lifecycle:lifecycle-common-java8:${Versions.observe_event_version}"
}