plugins {
    application
    kotlin("jvm") version "1.6.10"
}

group = "me.duffy"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("modules.BootKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.6.0")
    implementation("ch.qos.logback:logback-classic:1.2.10")
    implementation("com.github.ajalt.mordant:mordant:2.0.0-beta4")
    implementation("io.ktor:ktor-server-core:1.6.7")
    implementation("io.ktor:ktor-server-netty:1.6.7")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0-native-mt")
    testImplementation("io.ktor:ktor-server-tests:1.6.7")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

