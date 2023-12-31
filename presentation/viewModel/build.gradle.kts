plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.viewmodel"
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

    implementation(project(Modules.DOMAIN_USE_CASES))
    implementation(Dependencies.androidxCore)
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.junitExtension)
    implementation (Dependencies.hilt)
    implementation(Dependencies.coroutines)
    implementation(Dependencies.lifecycleViewModel)
    implementation(Dependencies.gson)
    implementation(Dependencies.apacheText)
    implementation(Dependencies.paging)
    ksp(Dependencies.hiltCompiler)

}