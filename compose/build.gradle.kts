import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose") version "2.3.0"
    id("maven-publish")
}

android {
    namespace = "com.github.rumboalla.kryptostore.compose"
    compileSdk = 36

    defaultConfig {
        minSdk = 23
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_1_8
        }
    }

    testOptions {
        targetSdk = 36
    }
}

dependencies {
    api(project(":core"))
    api("androidx.compose.ui:ui:1.10.0")
    api("androidx.lifecycle:lifecycle-runtime-compose:2.10.0")

    testImplementation("junit:junit:4.13.2")

    androidTestImplementation(project(":core"))
    androidTestImplementation("androidx.test:runner:1.7.0")
    androidTestImplementation("androidx.test.ext:junit:1.3.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4-android:1.10.0")
    androidTestImplementation("androidx.compose.ui:ui-test-manifest:1.10.0")
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("maven") {
                groupId = "com.github.rumboalla.kryptostore"
                artifactId = "compose"
                version = "0.1.5"
                from(components["release"])
            }
        }
    }
}
