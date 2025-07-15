plugins {
    java
    id("application")
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("org.springframework.boot") version "3.5.3"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.pm"
version = "0.0.1-SNAPSHOT"

application {
    mainClass.set("com.pm.codegenfarm.CodegenFarmApplication")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
    implementation("org.flywaydb:flyway-core")
    implementation("org.postgresql:postgresql")

    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")

    implementation("com.github.jsqlparser:jsqlparser:4.5")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
