plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

android {
    namespace = "com.github.rumboalla.kryptostore.keystore"
    compileSdk = 34

    defaultConfig {
        minSdk = 23
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    testOptions {
        targetSdk = 34
    }
}

dependencies {
    api(project(":core"))

    testImplementation("junit:junit:4.13.2")

    androidTestImplementation(project(":gson"))
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.github.rumboalla.kryptostore"
            artifactId = "keystore"
            version = "0.1.0"
        }
    }
}