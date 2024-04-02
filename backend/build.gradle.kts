plugins {
	java
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.cruz"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.12.0")
    testImplementation("io.projectreactor:reactor-test:3.6.4")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
