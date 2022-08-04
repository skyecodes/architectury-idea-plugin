import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.intellij") version "1.7.0"
    // Corresponds to IDEA 2022.1, see KotlinVersion class in ideaIC/3rd-party-rt.jar
    kotlin("jvm") version "1.6.10"
    id("org.jmailen.kotlinter") version "3.11.1"
}

group = "dev.architectury"
version = "1.6.2"

repositories {
    mavenCentral()
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    version.set("2022.1")
    plugins.set(listOf("java", "Kotlin"))
}

tasks {
    jar {
        from("COPYING", "COPYING.LESSER")
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }

    patchPluginXml {
        sinceBuild.set("221")
        untilBuild.set("222.*")
    }
}

kotlinter {
    disabledRules = arrayOf(
        "filename",
        "argument-list-wrapping",
        "trailing-comma",
    )
}
