package com.mjpecora.listeningparty.buildsrc

object Libs {
    const val jdkDesugar = "com.android.tools:desugar_jdk_libs:1.1.5"

    object AndroidX {

        object Activity {
            private const val version = "1.4.0"
            const val compose = "androidx.activity:activity-compose:$version"
        }

        object AppCompat {
            private const val version = "1.4.0"
            const val appcompat = "androidx.appcompat:appcompat:$version"
        }

        object Compose {
            private const val version = "1.0.5"

            const val foundation = "androidx.compose.foundation:foundation:$version"
            const val material = "androidx.compose.material:material-icons-core:$version"
            const val materialIconsCore = "androidx.compose.material:material-icons-extended:$version"
            const val materialIconsExtended = "androidx.compose.material:material:$version"
            const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:$version"
            const val ui = "androidx.compose.ui:ui:$version"
            const val uiTooling = "androidx.compose.ui:ui-tooling:$version"
        }

        object ConstraintLayout {
            private const val version = "1.0.0-rc02"
            const val constraintlayout = "androidx.constraintlayout:constraintlayout-compose:$version"
        }

        object Core {
            private const val version = "1.7.0"
            const val ktx = "androidx.core:core-ktx:$version"
        }

        object Hilt {
            private const val hiltVersion = "2.40.5"
            const val hiltAndroid = "com.google.dagger:hilt-android:$hiltVersion"
            const val compiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"

            private const val navigationVersion = "1.0.0-rc01"
            const val composeNavigation = "androidx.hilt:hilt-navigation-compose:$navigationVersion"
        }

        object Lifecycle {
            private const val version = "2.4.0"

            const val extensions = "androidx.lifecycle:lifecycle-extensions:2.2.0"
            const val livedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"

            private const val versionUpdated = "2.4.0"
            const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$versionUpdated"
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
            private const val version = "3.1.0"
            const val runtimeKtx = "androidx.paging:paging-runtime-ktx:$version"

            private const val composeVersion = "1.0.0-alpha14"
            const val compose = "androidx.paging:paging-compose:$composeVersion"
        }

        object Room {
            private const val version = "2.4.0"
            const val room = "androidx.room:room-runtime:$version"
            const val ktx = "androidx.room:room-ktx:$version"
            const val compiler = "androidx.room:room-compiler:$version"
            const val paging = "androidx.room:room-paging:$version"
        }
    }

    object Accompanist {
        private const val version = "0.22.0-rc"
        const val insets = "com.google.accompanist:accompanist-insets:$version"
        const val navigationAnimations = "com.google.accompanist:accompanist-navigation-animation:$version"
    }

    object Coil {
        private const val version = "1.4.0"
        const val compose = "io.coil-kt:coil-compose:$version"
    }

    object Coroutines {
        private const val version = "1.6.0"
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
    }

    object Firebase {
        private const val version = "29.0.3"
        const val bom = "com.google.firebase:firebase-bom:$version"
        const val auth = "com.google.firebase:firebase-auth-ktx"
        const val database = "com.google.firebase:firebase-database-ktx"

        const val authUi = "com.firebaseui:firebase-ui-auth:8.0.0"
    }

    object Google {
        private const val version = "19.2.0"
        const val auth = "com.google.android.gms:play-services-auth:$version"
    }

    object Gson {
        private const val version = "2.8.5"
        const val gson = "com.google.code.gson:gson:$version"
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

    object ML {
        private const val version = "17.0.1"
        const val textRecognition = "com.google.android.gms:play-services-mlkit-text-recognition:$version"
    }

    object Networking {
        private const val retrofitVersion = "2.9.0"
        const val retrofit2 = "com.squareup.retrofit2:retrofit:$retrofitVersion"

        private const val loggingVersion = "4.9.3"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$loggingVersion"

        private const val converterVersion = "0.8.0"
        const val serializationConverter = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:$converterVersion"

        private const val serializationVersion = "1.3.2"
        const val serializationCore = "org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion"
        const val serializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion"
    }

    object TensorFlow {
        private const val version = "2.2.0"
        const val tensorFlow = "org.tensorflow:tensorflow-lite:$version"

        private const val metaVersion = "0.3.0"
        const val metaData = "org.tensorflow:tensorflow-lite-metadata:$metaVersion"
        const val taskText = "org.tensorflow:tensorflow-lite-task-text:$metaVersion"
        const val delegatePlugin = "org.tensorflow:tensorflow-lite-gpu-delegate-plugin:$metaVersion"
    }

}