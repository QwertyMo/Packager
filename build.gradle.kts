
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    `maven-publish`
    application
}

group = "ru.kettuproj"
version = "1.0.5"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.10")
    implementation("io.netty:netty-buffer:4.1.90.Final")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "ru.kettuproj"
            artifactId = "Packager"
            version = "1.0.5"

            from(components["java"])
        }
    }
}