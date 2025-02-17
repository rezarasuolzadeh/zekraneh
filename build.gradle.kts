buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        val gradle = "8.8.1"
        classpath("com.android.tools.build:gradle:$gradle")

        val kotlin = "1.9.22"
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin")
    }
}