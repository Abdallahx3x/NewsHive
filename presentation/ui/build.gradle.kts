plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    buildFeatures {
        compose = true
    }
    kapt {
        correctErrorTypes = true
    }

}

dependencies {

    implementation(project(Modules.PRESENTATION_VIEW_MODEL))

    implementation(Dependencies.androidxCore)
    implementation(Dependencies.lifecycleRuntime)
    implementation(Dependencies.activityCompose)
    implementation(platform(Dependencies.composeBom))
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeUiGraphics)
    implementation(Dependencies.composeUiPreviewTool)
    implementation(Dependencies.coil)
    implementation(Dependencies.navigationCompose)
    implementation (Dependencies.systemUiController)
    implementation (Dependencies.accompanistPager)
    implementation (Dependencies.accompanistPagerIndicators)

    implementation(Dependencies.composeMaterial3)
    implementation(Dependencies.hilt)
    kapt(Dependencies.hiltCompiler)


    implementation(Dependencies.hiltNavigation)
    implementation(Dependencies.composeUtil)

    implementation(Dependencies.lifecycleViewModel)
    implementation(Dependencies.lifecycleRuntime)


    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.junitExtension)
    androidTestImplementation(platform(Dependencies.composeBom))
    androidTestImplementation(Dependencies.composeJunit)
    debugImplementation(Dependencies.composeUiTooling)
    debugImplementation(Dependencies.composeTestManifest)
}