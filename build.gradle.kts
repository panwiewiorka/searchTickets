// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    id("com.google.devtools.ksp") version "2.0.0-1.0.21"
    id ("androidx.room") version "2.6.1" apply false
}
true // Needed to make the Suppress annotation work for the plugins block

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath (libs.hilt.android.gradle.plugin)
    }
}