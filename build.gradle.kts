plugins {
    java
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "org.cerroteberes"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Habilitar Eureka para descubrimiento de servicios
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:3.1.7")
    // https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-openfeign
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.1.3")
    implementation("com.h2database:h2")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")

    implementation("com.auth0:java-jwt:4.4.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    runtimeOnly("com.mysql:mysql-connector-j")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("me.paulschwarz:spring-dotenv:3.0.0")

    // Agregar Lombok
    compileOnly("org.projectlombok:lombok:1.18.26")
    annotationProcessor("org.projectlombok:lombok:1.18.26")

    // Agregar Jakarta Bean Validation (Hibernate Validator)
    implementation("org.hibernate.validator:hibernate-validator:8.0.0.Final")
    implementation("jakarta.validation:jakarta.validation-api:3.0.0")

    // Agregar MapStruct
    implementation("org.mapstruct:mapstruct:1.5.2.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.2.Final")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
