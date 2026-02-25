plugins {
    id("java-library")
    id("chirp.spring-boot-service")
}

group = "com.github.orlandroyd"
version = "unspecified"

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
    api(libs.kotlin.reflect)
    api(libs.jackson.module.kotlin)

    implementation(libs.spring.boot.starter.amqp)
    implementation(libs.spring.boot.starter.security)

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}