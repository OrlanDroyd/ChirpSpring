plugins {
	id("chirp.spring-boot-app")
}

group = "com.github.orlandroyd"
version = "0.0.1-SNAPSHOT"
description = "Chirp Backend"

dependencies {
	implementation(projects.user)
	implementation(projects.chat)
	implementation(projects.notification)
	implementation(projects.common)
}
