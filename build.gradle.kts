buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        val gradle = "7.2.1"
        classpath("com.android.tools.build:gradle:$gradle")

        val kotlin = "1.6.10"
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin")
    }
}