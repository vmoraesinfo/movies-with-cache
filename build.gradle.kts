plugins {
	java
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.moraes"
version = "0.0.1-SNAPSHOT"
description = "CRUD Movies"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {

	implementation("org.springframework.boot:spring-boot-starter-webflux")

	implementation("org.springframework.boot:spring-boot-starter-cache")

	implementation("org.springframework.boot:spring-boot-starter-data-redis")

	developmentOnly("org.springframework.boot:spring-boot-devtools")

	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
	testImplementation("org.mockito:mockito-core:5.5.0")
	testImplementation("com.squareup.okhttp3:mockwebserver:4.12.0")
	testImplementation("io.projectreactor:reactor-test")
}


tasks.withType<Test> {
	useJUnitPlatform()
}
tasks.test {
useJUnitPlatform()
}