plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.remote"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField( "String", "API_KEY", "${properties["API_KEY"]}")
        buildConfigField( "String", "BASE_URL", "${properties["BASE_URL"]}")
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
        buildConfig = true
    }
}

dependencies {


    implementation(project(Modules.DATA_REPOSITORIES))
    implementation(project(Modules.DOMAIN_USE_CASES))



    implementation(Dependencies.androidxCore)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.gsonConverter)
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.junitExtension)
    androidTestImplementation(Dependencies.gsonConverter)
    implementation (Dependencies.hilt)


}