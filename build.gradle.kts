// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
}

buildscript {

    repositories {
        // Make sure that you have the following two repositories
        google()  // Google's Maven repository
        mavenCentral()
    // Maven Central repository
    }

    dependencies {


        // Add the Maven coordinates and latest version of the plugin
        classpath ("com.google.gms:google-services:4.4.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
    }
}

