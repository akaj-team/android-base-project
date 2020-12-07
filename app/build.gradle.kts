plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(30)
    buildToolsVersion = "29.0.3"

    defaultConfig {
        applicationId = "com.android.appname"
        minSdkVersion(23)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0.0"

        setTestInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
            setSigningConfig(signingConfigs.getByName("debug"))
            isTestCoverageEnabled = true
        }

        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            setSigningConfig(signingConfigs.getByName("debug"))
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    applicationVariants.forEach { variant ->
        when (variant.name) {
            "debug" -> {
                variant.buildConfigField("String", "BASE_API_URL", "\"https://api.github.com/\"")
            }
            "release" -> {
                variant.buildConfigField("String", "BASE_API_URL", "\"\"")
            }
        }
    }

    lintOptions {
        isAbortOnError = false
        lintConfig = file("lint.xml")
        xmlOutput = file("${buildDir}/reports/lint-results.xml")
    }

    testOptions {
        unitTests.all {
            it.ignoreFailures = true
            it.include("**/*Test.class")
            it.exclude("**/element/*Test.class")
        }
        unitTests.isReturnDefaultValues = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

val kotlinVersion = "1.4.20"
val roomVersion = "2.2.5"

dependencies {
    implementation(fileTree("dir" to "libs", "include" to "*.jar"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.fragment:fragment-ktx:1.3.0-beta02")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    // Test
    testImplementation("junit:junit:4.13.1")
    testImplementation("org.mockito:mockito-inline:2.21.0")
    testImplementation("org.hamcrest:hamcrest-core:2.2")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.9.0")
    testImplementation("org.robolectric:robolectric:4.4")

    // android architecture viewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.0-beta01")

    // ConstraintLayout
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")

    // RecycleView
    implementation("androidx.recyclerview:recyclerview:1.1.0")

    // Retrofit 2
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.11.0") {
        exclude(group = ("com.android.support"))
    }
    kapt("com.github.bumptech.glide:compiler:4.11.0")

    // Rxjava - RxAndroid
    implementation("io.reactivex.rxjava2:rxjava:2.2.10")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.9.0")

    api("com.google.dagger:dagger-android:2.30.1")
    api("com.google.dagger:dagger-android-support:2.30.1")
    kapt("com.google.dagger:dagger-android-processor:2.30.1")
    kapt("com.google.dagger:dagger-compiler:2.30.1")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.4.20")
    implementation("androidx.preference:preference-ktx:1.1.1")

    // Room
    implementation("androidx.room:room-runtime:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    debugImplementation("com.amitshekhar.android:debug-db:1.0.6")
}

apply(from = "ci-kotlin.gradle")
apply(from = "jacoco.gradle")
