plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "vn.edu.hust.listemail"
    compileSdk = 34

    defaultConfig {
        applicationId = "vn.edu.hust.listemail"
        minSdk = 9
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("com.android.support:appcompat-v7:25.4.0")
    implementation("com.android.support.constraint:constraint-layout:1.0.2")
    implementation("com.android.support:recyclerview-v7:25.4.0")
    testImplementation("unit:junit:4.12")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("com.android.support.test:runner:1.0.0")
    androidTestImplementation("com.android.support.test.espresso:espresso-core:3.0.0")
}