import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.12"
	id("io.spring.dependency-management") version "1.1.3"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"  
        id("org.sonarqube") version "4.4.1.3373"
}  
         
group = "com.uahannam"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

sonar {
	properties {
		property("sonar.projectKey", "ua-hannam_order-service_AYyBRA09EBpTkGCeVl3i")
		property("sonar.host.url", "http://211.205.161.133:9000")
		property("sonar.login", "squ_d3bfd77d128148710aadd41852ce48d5fcd078b9")
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	runtimeOnly("com.h2database:h2")
	runtimeOnly("io.r2dbc:r2dbc-h2")
//	implementation ("org.postgresql:postgresql")
//	implementation ("org.postgresql:r2dbc-postgresql")


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
