plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.parivikshaka"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.parivikshaka"
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

        buildFeatures {
            dataBinding = true
            viewBinding = true
        }


    }

    dependencies {
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.appcompat)
        implementation(libs.material)
        implementation(libs.androidx.activity)
        implementation(libs.androidx.constraintlayout)
        implementation(libs.androidx.legacy.support.v4)
        implementation(libs.androidx.lifecycle.livedata.ktx)
        implementation(libs.androidx.fragment.ktx)
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.1.3")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

        // Architectural Components
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")


        // Coroutines
        implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
        implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")


        // Coroutine Lifecycle Scopes
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

        // Retrofit
        implementation("com.squareup.retrofit2:retrofit:2.9.0")
        implementation("com.squareup.retrofit2:converter-gson:2.9.0")
        implementation("com.squareup.okhttp3:logging-interceptor:4.5.0")

        // Navigation Components
        implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
        implementation("androidx.navigation:navigation-ui-ktx:2.7.7")


        // Glide
        implementation("com.github.bumptech.glide:glide:4.14.2")
        kapt("com.github.bumptech.glide:compiler:4.11.0")

        implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
        implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

        implementation("androidx.core:core-ktx:1.12.0")
        implementation("com.google.dagger:hilt-android:2.48.1")
        kapt("com.google.dagger:hilt-android-compiler:2.48.1")
        kapt("androidx.hilt:hilt-compiler:1.0.0")
        implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")

        implementation ("androidx.activity:activity-ktx:1.8.2")



        implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
        implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")


        //moshi
        implementation("com.squareup.moshi:moshi-kotlin:1.12.0")
        implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")

        implementation("androidx.recyclerview:recyclerview:1.3.2")

    }

