plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.ui"
    compileSdk = 33

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(project(Modules.PRESENTATION_VIEW_MODEL))


    implementation(Dependencies.androidxCore)
    implementation(Dependencies.androidxAppcompat)
    implementation(Dependencies.androidMaterial)
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.junitExtension)
    androidTestImplementation(Dependencies.espresso)
}