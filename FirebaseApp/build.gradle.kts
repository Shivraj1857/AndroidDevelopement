// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    //root,project nad top level
    alias(libs.plugins.android.application) apply false
    id("com.google.gms.google-services") version "4.4.4" apply false
}