plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation("com.android.tools.build:gradle:7.0.4")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.38.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
    implementation("org.jetbrains.kotlin:kotlin-serialization:1.5.31")
    implementation("com.google.gms:google-services:4.3.10")
}
