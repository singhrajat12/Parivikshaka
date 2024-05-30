


// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    id ("com.google.dagger.hilt.android") version "2.48" apply false

}

//buildscript {
//
//    dependencies {
//        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.44.1")
//        classpath ("com.google.gms:google-services:4.4.1")
//
//        // Add any other dependencies needed for the build script here
//    }



