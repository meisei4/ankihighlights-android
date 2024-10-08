plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.android.ksp)
    alias(libs.plugins.kotlin.plugin.compose)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.ankihighlights.android"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ankihighlights.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.ankihighlights.android.AnkiHighlightsTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            val flaskApiUrl: String? = project.findProperty("FLASK_API_URL") as String?
            if (flaskApiUrl != null) {
                buildConfigField("String", "BASE_URL", "\"$flaskApiUrl\"")
            } else {
                throw IllegalStateException("FLASK_API_URL must not be null")
            }
        }

        release {
            // TODO: BuildConfig for production URL
            //  buildConfigField("String", "BASE_URL", "\"https://your-production-url.com\"")

            val useFakeRepository: String? = project.findProperty("USE_FAKE_REPOSITORY") as String?
            if (useFakeRepository != null) {
                buildConfigField("boolean", "USE_FAKE_REPOSITORY", useFakeRepository)
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.tracing.ktx)

    testImplementation(libs.androidx.compose.ui)

    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler) // TODO: implementation not kst/kpt anything other than ksp here causes huge issues

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)

    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.common)
    ksp(libs.androidx.room.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)

    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.hilt.android.testing)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
