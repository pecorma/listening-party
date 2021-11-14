package com.mjpecora.poke.buildsrc

object Libs {
    const val jdkDesugar = "com.android.tools:desugar_jdk_libs:1.1.5"

    object AndroidX {

        object Activity {
            private const val version = "1.4.0"
            const val compose = "androidx.activity:activity-compose:$version"
        }

        object AppCompat {
            private const val version = "1.3.1"
            const val appcompat = "androidx.appcompat:appcompat:$version"
        }

        object Compose {
            private const val version = "1.1.0-beta02"

            const val foundation = "androidx.compose.foundation:foundation:$version"
            const val material = "androidx.compose.material:material-icons-core:$version"
            const val material3 = "androidx.compose.material3:material3:1.0.0-alpha01"
            const val materialIconsCore = "androidx.compose.material:material-icons-extended:$version"
            const val materialIconsExtended = "androidx.compose.material:material:$version"
            const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:$version"
            const val ui = "androidx.compose.ui:ui:$version"
            const val uiTooling = "androidx.compose.ui:ui-tooling:$version"
        }

        object ConstraintLayout {
            private const val version = "2.1.1"
            const val constraintlayout = "androidx.constraintlayout:constraintlayout:$version"
        }

        object Core {
            private const val version = "1.7.0"
            const val ktx = "androidx.core:core-ktx:$version"
        }

        object Hilt {
            private const val hiltVersion = "2.38.1"
            const val hiltAndroid = "com.google.dagger:hilt-android:$hiltVersion"
            const val compiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"

            private const val navigationVersion = "1.0.0-alpha03"
            const val composeNavigation = "androidx.hilt:hilt-navigation-compose:$navigationVersion"
        }

        object Lifecycle {
            private const val version = "2.2.0"

            const val extensions = "androidx.lifecycle:lifecycle-extensions:$version"
            const val livedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"

            private const val versionUpdated = "2.4.0"
            const val viewmodelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$versionUpdated"
        }

        object Navigation {
            private const val version = "2.4.0-beta02"
            const val compose = "androidx.navigation:navigation-compose:$version"
        }

        object Palette {
            private const val version = "1.0.0"
            const val palette = "androidx.palette:palette:$version"
        }

        object Paging {
            private const val version = "3.1.0-rc01"
            const val runtimeKtx = "androidx.paging:paging-runtime-ktx:$version"

            private const val composeVersion = "1.0.0-alpha14"
            const val compose = "androidx.paging:paging-compose:$composeVersion"
        }
    }

    object Accompanist {
        private const val version = "0.21.1-beta"
        const val insets = "com.google.accompanist:accompanist-insets:$version"
    }

    object Coil {
        private const val version = "1.4.0"
        const val compose = "io.coil-kt:coil-compose:$version"
    }

    object Coroutines {
        private const val version = "1.5.0"
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
    }

    object Kotlin {
        private const val version = "1.5.31"
        const val serialization = "org.jetbrains.kotlin:kotlin-serialization:$version"
        const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
    }

    object Material {
        private const val version = "1.4.0"
        const val material = "com.google.android.material:material:$version"
    }

    object Networking {
        private const val retrofitVersion = "2.9.0"
        const val retrofit2 = "com.squareup.retrofit2:retrofit:$retrofitVersion"

        private const val loggingVersion = "4.9.1"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$loggingVersion"

        private const val converterVersion = "0.8.0"
        const val serializationConverter = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:$converterVersion"

        private const val serializationVersion = "1.0.1"
        const val serializationCore = "org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion"
        const val serializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion"
    }

}


//ext {
//    appcompat_version = "1.3.1"
//    compose_version = "1.1.0-beta02"
//    constraintlayout_version = "2.1.1"
//    core_ktx_version = "1.7.0"
//    coroutines_version = "1.5.0"
//    fragment_version = "1.3.6"
//    glide_version = "4.12.0"
//    kotlin_serialization_converter_version = "0.8.0"
//    kotlin_serialization_version = "1.0.1"
//    kotlin_version = "1.5.31"
//    lifecycle_version = "2.2.0"
//    material_version = "1.4.0"
//    okhttp_logging_version = "4.9.1"
//    paging_version = "3.1.0-rc01"
//    retrofit_version = "2.9.0"
//}
