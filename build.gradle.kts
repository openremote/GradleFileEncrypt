import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    kotlin("jvm") version "1.9.25"
    id("java-gradle-plugin")
    id("maven-publish")
    id("com.github.ben-manes.versions") version "0.51.0"
    id("io.gitlab.arturbosch.detekt") version "1.23.8"
    id("com.gradle.plugin-publish") version "2.0.0"
}

group = "com.cherryperry.gfe"
version = "2.0.3"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        apiVersion = "1.9"
    }
}

detekt {
    config = files("detekt.yml")
    baseline = file("detekt-baseline.xml")
}

gradlePlugin {
    website = "https://github.com/CherryPerry/GradleFileEncrypt"
    vcsUrl = "https://github.com/CherryPerry/GradleFileEncrypt.git"
    plugins.register("gradleFileEncryptPlugin") {
        id = "com.cherryperry.gradle-file-encrypt"
        displayName = "Gradle file encrypt"
        description = "Simply encrypt your sensitive data in repository with password"
        tags = listOf("encryption", "cryptography")
        implementationClass = "com.cherryperry.gfe.FileEncryptPlugin"
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.eclipse.jgit", "org.eclipse.jgit", "6.1.0.202203080745-r")
    compileOnly(gradleApi())
    testImplementation("junit", "junit", "4.13.2")
    testImplementation(gradleTestKit())
    detektPlugins("io.gitlab.arturbosch.detekt", "detekt-formatting", "1.23.8")
    detektPlugins("io.gitlab.arturbosch.detekt", "detekt-rules-libraries", "1.23.8")
}
