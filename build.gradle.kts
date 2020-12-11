// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${com.android.appname.buildsrc.Libs.BuildGradle.VERSION}")
        classpath("com.deploygate:gradle:2.4.0-rc01")
        classpath(
            kotlin(
                "gradle-plugin",
                version = com.android.appname.buildsrc.Libs.Kotlin.VERSION
            )
        )

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
