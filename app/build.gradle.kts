plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.projemanage"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.projemanage"
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
    kotlinOptions {
        jvmTarget = "1.8"
    }

    viewBinding {
        enable = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.material:material:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

//    TODO if you need, Import the Firebase BoM
//    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
//    TODO 使いたいfirebaseプロダクトにより依存関係を追加してください BoMを使う場合は依存関係のバージョンは特段必要ない
//    implementation("com.google.firebase:firebase-analytics-ktx:21.3.0")
//    implementation("com.google.gms:google-services:4.4.0")


    implementation("com.google.firebase:firebase-auth:22.1.2")
//    // Firebase Cloud Firestore Dependency
    implementation("com.google.firebase:firebase-firestore:24.8.1")
}