tasks.getByName("check").dependsOn("detekt")
val detekt: Configuration by configurations.creating

dependencies {
    detekt("io.gitlab.arturbosch.detekt:detekt-cli:1.14.2")
    detekt("io.gitlab.arturbosch.detekt:detekt-formatting:1.14.2")
}

tasks.register("generalApplocationSuffix") {
    if (System.getenv("CI") != null) {
        doLast {
            rootProject.extra["applicationIdSuffix"] =
                java.text.SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(java.util.Date())
        }
    }
}

tasks.register<JavaExec>("detekt") {
    val config = if (project.hasProperty("detektConfigFile")) {
        file(project.properties["detektConfigFile"] ?: "detekt.yml")
    } else {
        file("detekt.yml")
    }

    main = "io.gitlab.arturbosch.detekt.cli.Main"
    classpath = detekt
    val reportXml = "xml:$projectDir/build/reports/detekt/detekt-checkstyle.xml"
    val reportText = "xml:$projectDir/build/reports/detekt/detekt-plain.txt"
    val exclude = "(?i).*src/.*test.*/.*;.*Dummy.*"
    val input = "$rootDir"
    val params = arrayOf("-i", input, "-c", config, "-ex", exclude, "-r", reportXml, "-r", reportText, "--auto-correct")
    args(*params)
}
