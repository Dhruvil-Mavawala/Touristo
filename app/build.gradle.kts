plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.tourttavels"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.tourttavels"
        minSdk = 24
        targetSdk = 34
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.database)
    implementation(libs.firebase.messaging)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation ("com.airbnb.android:lottie:6.5.1")
    implementation ("com.google.android.material:material:1.2.1")
    implementation ("de.hdodenhof:circleimageview:3.1.0")
    implementation("com.google.firebase:firebase-storage:21.0.0")
    implementation("com.firebaseui:firebase-ui-database:8.0.0")
    implementation ("com.github.dhaval2404:imagepicker:2.1")
    implementation ("com.google.android.gms:play-services-auth:21.2.0")
    implementation ("androidx.appcompat:appcompat:1.6.1'")
    implementation ("androidx.drawerlayout:drawerlayout:1.1.1")
    implementation ("com.orhanobut:dialogplus:1.11@aar")
    implementation ("androidx.biometric:biometric:1.1.0")


}