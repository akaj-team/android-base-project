buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath("com.deploygate:gradle:2.4.0-rc01")
    }
}
plugins.apply(com.deploygate.gradle.plugins.DeployGatePlugin::class)

configure<com.deploygate.gradle.plugins.dsl.DeployGateExtension> {
    appOwnerName = ""
    apiToken = ""
    deployments {
        create("atDebug") {
            message = "git log --oneline -1".runCommand()
        }

        create("atStaging") {
            message = "git log --oneline -1".runCommand()
        }
    }
}

fun String.runCommand(workingDir: File = file("./")): String {
    val parts = this.split("\\s".toRegex())
    val proc = ProcessBuilder(*parts.toTypedArray())
        .directory(workingDir)
        .redirectOutput(ProcessBuilder.Redirect.PIPE)
        .redirectError(ProcessBuilder.Redirect.PIPE)
        .start()

    proc.waitFor(1, TimeUnit.MINUTES)
    return proc.inputStream.bufferedReader().readText().trim()
}
