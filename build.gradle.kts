plugins {
    kotlin("jvm") version "2.0.0"
    `maven-publish`
    application
}

group = "ru.kettuproj"
version = "1.0.8"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlin:kotlin-reflect:2.0.0")
    implementation("io.netty:netty-buffer:4.1.110.Final")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("MainKt")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "ru.kettuproj"
            artifactId = "Packager"
            version = "1.0.7"

            from(components["java"])
        }
    }
}