apply(plugin = "jacoco")
tasks.register<JacocoReport>("jacocoTestReport") {
    dependsOn(tasks.getByName("testDebugUnitTest"))
    reports {
        xml.isEnabled = true
        html.isEnabled = true
    }
    val fileFilter = arrayOf(
        "**/R.class", "**/R$*.class", "**/BuildConfig.*",
        "**/Manifest*.*", "**/*Test*.*", "android/**/*.*",
        "**/*UI.**", "**/*Exception.**", "**/*Response.**",
        //Option, will remove this below
        "**/*Repository.**"
    )
    val include = listOf("**/*ViewModel.*", "**/*DataSource.**")
    val exclude = listOf("**/*Dummy*.*")
    val mainSrc = "${project.projectDir}/src/main/java"
    val debugTree = fileTree(
        "dir" to "${buildDir}/intermediates/classes/Debug",
        "includes" to include,
        "excludes" to exclude
    )
    val kotlinDebugTree = fileTree(
        "dir" to "${buildDir}/tmp/kotlin-classes/Debug",
        "includes" to include,
        "excludes" to exclude
    )
    sourceDirectories.from(files(mainSrc))
    classDirectories.from(files(debugTree, kotlinDebugTree))
    executionData.from(files("${buildDir}/jacoco/testDebugUnitTest.exec"))
}

tasks.getByName("check").finalizedBy("jacocoTestReport")

tasks.withType(Test::class) {
    configure<JacocoTaskExtension> {
        isIncludeNoLocationClasses = true
        excludes = listOf("jdk.internal.*")
    }
}
