import com.android.appname.buildsrc.Libs

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    kotlin("android.extensions")
//    id("kotlin-parcelize")
}

android {
    compileSdkVersion(30)

    defaultConfig {
        applicationId = "com.android.appname"
        minSdkVersion(23)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0.0"

        setTestInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
    }

    signingConfigs {
        getByName("debug") {
            storeFile = rootProject.file("keystores/debug.jks")
        }
        create("release") {
            storeFile = rootProject.file("keystores/release.jks")
            storePassword = System.getenv("ANDROID_KEYSTORE_PASSWORD")
            keyAlias = System.getenv("ANDROID_KEYSTORE_ALIAS")
            keyPassword = System.getenv("ANDROID_KEYSTORE_PRIVATE_KEY_PASSWORD")
        }
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("debug")
            isTestCoverageEnabled = true
        }

        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("debug")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    this.applicationVariants.configureEach {
        when (name) {
            "debug" -> {
                buildConfigField("String", "BASE_API_URL", "\"https://api.github.com/\"")
            }
            "release" -> {
                buildConfigField("String", "BASE_API_URL", "\"https://api.github.com/\"")
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

dependencies {
    implementation(fileTree("dir" to "libs", "include" to "*.jar"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Libs.Kotlin.VERSION}")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.fragment:fragment-ktx:1.3.0-beta02")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    // Test
    testImplementation("junit:junit:4.13.1")
    testImplementation("org.mockito:mockito-inline:2.21.0")
    androidTestImplementation("org.mockito:mockito-android:2.21.0")
    testImplementation("org.mockito:mockito-core:2.21.0")
    testImplementation("net.bytebuddy:byte-buddy-agent:1.10.18")
    testImplementation("net.bytebuddy:byte-buddy:1.10.18")
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

    api("com.google.dagger:dagger-android:${Libs.Dagger.VERSION}")
    api("com.google.dagger:dagger-android-support:${Libs.Dagger.VERSION}")
    kapt("com.google.dagger:dagger-android-processor:${Libs.Dagger.VERSION}")
    kapt("com.google.dagger:dagger-compiler:${Libs.Dagger.VERSION}")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.4.20")
    implementation("androidx.preference:preference-ktx:1.1.1")

    // Room
    implementation("androidx.room:room-runtime:${Libs.Room.VERSION}")
    kapt("androidx.room:room-compiler:${Libs.Room.VERSION}")
    implementation("androidx.room:room-ktx:${Libs.Room.VERSION}")
    debugImplementation("com.amitshekhar.android:debug-db:1.0.6")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Libs.Coroutines.VERSION}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Libs.Coroutines.VERSION}")
}

apply(from = "ci-kotlin.gradle.kts")
apply(from = "jacoco.gradle.kts")
apply(from = "deploygate.gradle.kts")
