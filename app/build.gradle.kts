import com.mjpecora.listeningparty.buildsrc.Libs
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
    id("com.google.gms.google-services")
}

android {
    compileSdk = 31
    buildToolsVersion ="30.0.3"

    defaultConfig {
        applicationId = "com.mjpecora.listeningparty"
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
            freeCompilerArgs = freeCompilerArgs.toMutableList().apply {
                add("-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi")
                add("-Xopt-in=kotlinx.coroutines.FlowPreview")
                add("-Xopt-in=kotlinx.serialization.ExperimentalSerializationApi")
                add("-Xopt-in=androidx.compose.foundation.ExperimentalFoundationApi")
                add("-Xopt-in=coil.annotation.ExperimentalCoilApi")
                add("-Xopt-in=androidx.compose.material.ExperimentalMaterialApi")
                add("-Xopt-in=androidx.compose.animation.ExperimentalAnimationApi")
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

    allprojects {
        tasks.withType<DependencyUpdatesTask> {
            rejectVersionIf {
                isNonStable(this.candidate.version)
            }
        }
    }
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

dependencies {
    implementation(fileTree("../libs"))
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
    implementation(Libs.AndroidX.Lifecycle.viewModelKtx)

    implementation(Libs.AndroidX.Navigation.compose)

    implementation(Libs.Networking.retrofit2)
    implementation(Libs.Networking.loggingInterceptor)
    implementation(Libs.Networking.serializationConverter)
    implementation(Libs.Networking.serializationJson)
    implementation(Libs.Networking.serializationCore)
    implementation(Libs.Gson.gson)
    implementation(Libs.Coil.compose)

    implementation(Libs.Coroutines.coroutinesAndroid)
    implementation(Libs.Coroutines.core)

    implementation(Libs.AndroidX.Palette.palette)
    implementation(Libs.AndroidX.Paging.runtimeKtx)
    implementation(Libs.AndroidX.Paging.compose)
    implementation(Libs.AndroidX.Compose.material3)
    implementation(Libs.Accompanist.insets)
    implementation(Libs.Accompanist.navigationAnimations)
    implementation(Libs.AndroidX.Compose.foundation)
    implementation(Libs.AndroidX.Compose.material)
    implementation(Libs.AndroidX.Compose.materialIconsCore)
    implementation(Libs.AndroidX.Compose.materialIconsExtended)
    implementation(Libs.AndroidX.Compose.runtimeLivedata)
    implementation(Libs.AndroidX.Compose.ui)
    implementation(Libs.AndroidX.Compose.uiTooling)
    implementation(Libs.AndroidX.Lifecycle.viewModelCompose)
    implementation(Libs.AndroidX.Activity.compose)

    implementation(Libs.ML.textRecognition)

    implementation(platform(Libs.Firebase.bom))
    implementation(Libs.Firebase.auth)
    implementation(Libs.Google.auth)

    implementation(Libs.AndroidX.Room.room)
    implementation(Libs.AndroidX.Room.ktx)
    implementation(Libs.AndroidX.Room.paging)
    kapt(Libs.AndroidX.Room.compiler)

    coreLibraryDesugaring(Libs.jdkDesugar)

//    testImplementation 'junit:junit:4.13.2'
//    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
//    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
}