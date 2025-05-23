plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
}

android {
    namespace = "ir.rezarasuolzadeh.zekraneh"
    compileSdk = 35

    defaultConfig {
        applicationId = "ir.rezarasuolzadeh.zekraneh"
        targetSdk = 35
        minSdk = 23
        versionCode = 10
        versionName = "1.5.12"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    signingConfigs{
        create("release") {
            enableV1Signing = true
            enableV2Signing = true
        }
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.9.10")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    val hawk = "2.0.1"
    implementation("com.orhanobut:hawk:$hawk")

    val googleMaterial = "1.11.0"
    implementation("com.google.android.material:material:$googleMaterial")
}
