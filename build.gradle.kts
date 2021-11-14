import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    kotlin("android") apply false
    id("com.github.ben-manes.versions") version "0.39.0"
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin("com.github.ben-manes.versions")
    }
}

tasks {
    register("clean", Delete::class) {
        delete(rootProject.buildDir)
    }

    withType<DependencyUpdatesTask> {
        checkForGradleUpdate = true
        outputFormatter = "json"
        outputDir = "build/dependencyUpdates"
        reportfileName = "report"
    }
}