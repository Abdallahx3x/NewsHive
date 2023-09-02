plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("com.google.dagger.hilt.android")

}

android {
    namespace = "com.example.newshive"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.newshive"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion ="1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(Modules.PRESENTATION_UI))
    implementation(project(Modules.PRESENTATION_VIEW_MODEL))
    implementation(project(Modules.DOMAIN_USE_CASES))
    implementation(project(Modules.DATA_REPOSITORIES))
    implementation(project(Modules.DATA_REMOTE))
    implementation(project(Modules.DATA_LOCAL))
    implementation(project(Modules.DOMAIN_ENTITIES))


    implementation(Dependencies.androidxCore)
    implementation(Dependencies.lifecycleRuntime)
    implementation(Dependencies.activityCompose)
    implementation(platform(Dependencies.composeBom))
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeUiGraphics)
    implementation(Dependencies.composeUiPreviewTool)
    implementation(Dependencies.composeMaterial3)
    implementation(Dependencies.retrofit)
    implementation (Dependencies.gsonConverter)
    implementation (Dependencies.hilt)
//    implementation("com.squareup.okhttp3:okhttp:4.10.0")
   // implementation(platform("com..okhttp3:okhttp-bom:4.10.0"))


    kapt(Dependencies.hiltCompiler)
    implementation (Dependencies.logging)




    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.junitExtension)
    androidTestImplementation(platform(Dependencies.composeBom))
    androidTestImplementation(Dependencies.composeJunit)
    debugImplementation(Dependencies.composeUiTooling)
    debugImplementation(Dependencies.composeTestManifest)
    debugImplementation(Dependencies.composeTestManifest)



}