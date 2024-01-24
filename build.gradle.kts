import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.12"
	id("io.spring.dependency-management") version "1.1.3"
	id("org.sonarqube") version "4.4.1.3373"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"
	kotlin("plugin.jpa") version "1.4.32"
}  
         
group = "com.uahannam"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}



allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

dependencies {
	implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("io.mockk:mockk:1.9.2")
	testImplementation ("com.appmattus.fixture:fixture:1.2.0")

	testImplementation("io.kotest:kotest-runner-junit5:5.4.2")
	testImplementation("io.kotest:kotest-assertions-core:5.4.2")
	testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.2")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.testng:testng:7.1.0")
	runtimeOnly("com.h2database:h2")
}

sonar {
	properties {
		property("sonar.projectKey", "ua-hannam_order-service")
		property("sonar.organization", "msa2024")
		property("sonar.host.url", "https://sonarcloud.io")
	}
}


tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
