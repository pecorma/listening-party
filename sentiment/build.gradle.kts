import com.mjpecora.listeningparty.buildsrc.Libs

plugins {
    kotlin("android")
    id("com.android.library")
}

android {
    compileSdk = 31
    buildToolsVersion ="30.0.3"

    defaultConfig {
        minSdk = 23
        targetSdk = 31

      testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
      consumerProguardFiles("consumer-rules.pro")
    }

    aaptOptions {
        noCompress("tflite")
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true

        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        mlModelBinding = true
    }

}

dependencies {
    //implementation(Libs.TensorFlow.tensorFlow)
    implementation("androidx.annotation:annotation:1.0.0")
    implementation(Libs.TensorFlow.metaData)
    implementation(Libs.TensorFlow.taskText)
    implementation(Libs.TensorFlow.delegatePlugin)
}