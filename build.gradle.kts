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

val mockkVersion = "1.9.2"
val fixtureVersion = "1.2.0"
val kotestVersion = "5.4.2"
val kotestExtentionVersion = "1.1.2"
val testingVersion = "7.7.0"
val springMockVersion = "4.0.2"


dependencies {
	implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.kafka:spring-kafka")

	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign")

	testImplementation("io.mockk:mockk:${mockkVersion}")
	testImplementation("com.ninja-squad:springmockk:${springMockVersion}")
	testImplementation ("com.appmattus.fixture:fixture:${fixtureVersion}")

	testImplementation("io.kotest:kotest-runner-junit5:${kotestVersion}")
	testImplementation("io.kotest:kotest-assertions-core:${kotestVersion}")
	testImplementation("io.kotest.extensions:kotest-extensions-spring:${kotestExtentionVersion}")


	testImplementation("org.springframework.kafka:spring-kafka-test")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.testng:testng:${testingVersion}")

	runtimeOnly("com.h2database:h2")
}

sonar {
	properties {
		property("sonar.projectKey", "ua-hannam_order-service")
		property("sonar.organization", "msa2024")
		property("sonar.host.url", "https://sonarcloud.io")
	}
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:2020.0.4")
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
