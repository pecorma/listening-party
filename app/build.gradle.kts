import com.mjpecora.poke.buildsrc.Libs
import kotlin.collections.mapOf

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
}

android {
    compileSdk = 31
    buildToolsVersion ="30.0.3"

    defaultConfig {
        applicationId = "com.mjpecora.poke"
        minSdk = 23
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true",
                    "room.expandProjection" to "true"
                )
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true

        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = mutableListOf(
                "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-Xopt-in=kotlinx.coroutines.FlowPreview",
                "-Xopt-in=kotlinx.serialization.ExperimentalSerializationApi",
                "-Xopt-in=androidx.compose.foundation.ExperimentalFoundationApi",
                "-Xopt-in=coil.annotation.ExperimentalCoilApi",
            ).apply {
                this.addAll(freeCompilerArgs)
            }
        }
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.0-beta02"
    }
}

dependencies {
    implementation(Libs.Kotlin.stdLib)
    implementation(Libs.AndroidX.Core.ktx)
    implementation(Libs.AndroidX.AppCompat.appcompat)
    implementation(Libs.AndroidX.ConstraintLayout.constraintlayout)
    implementation(Libs.Material.material)

    implementation(Libs.AndroidX.Hilt.hiltAndroid)
    implementation(Libs.AndroidX.Hilt.composeNavigation)
    kapt(Libs.AndroidX.Hilt.compiler)

    implementation(Libs.AndroidX.Lifecycle.extensions)
    implementation(Libs.AndroidX.Lifecycle.livedataKtx)
    implementation(Libs.AndroidX.Lifecycle.viewmodelKtx)

    implementation(Libs.AndroidX.Navigation.compose)

    implementation(Libs.Networking.retrofit2)
    implementation(Libs.Networking.loggingInterceptor)
    implementation(Libs.Networking.serializationConverter)
    implementation(Libs.Networking.serializationJson)
    implementation(Libs.Networking.serializationCore)
    implementation(Libs.Coil.compose)

    implementation(Libs.Coroutines.coroutinesAndroid)
    implementation(Libs.Coroutines.core)

    implementation(Libs.AndroidX.Palette.palette)
    implementation(Libs.AndroidX.Paging.runtimeKtx)
    implementation(Libs.AndroidX.Paging.compose)
    implementation(Libs.AndroidX.Compose.material3)
    implementation(Libs.Accompanist.insets)
    implementation(Libs.AndroidX.Compose.foundation)
    implementation(Libs.AndroidX.Compose.material)
    implementation(Libs.AndroidX.Compose.materialIconsCore)
    implementation(Libs.AndroidX.Compose.materialIconsExtended)
    implementation(Libs.AndroidX.Compose.runtimeLivedata)
    implementation(Libs.AndroidX.Compose.ui)
    implementation(Libs.AndroidX.Compose.uiTooling)
    implementation(Libs.AndroidX.Lifecycle.viewmodelCompose)
    implementation(Libs.AndroidX.Activity.compose)

    implementation(Libs.AndroidX.Room.room)
    implementation(Libs.AndroidX.Room.ktx)
    implementation(Libs.AndroidX.Room.paging)
    kapt(Libs.AndroidX.Room.compiler)

    coreLibraryDesugaring(Libs.jdkDesugar)

//    testImplementation 'junit:junit:4.13.2'
//    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
//    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
}