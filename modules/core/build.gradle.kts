apply {
    plugin("org.jetbrains.kotlin.plugin.jpa")
}

tasks.getByName<Jar>("jar") {
    enabled = true
}

dependencies {
    api(project(":modules:contract"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-json")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation(group = "com.querydsl", name = "querydsl-jpa", classifier="jakarta")
    implementation("org.flywaydb:flyway-mysql")
    implementation("io.micrometer:micrometer-tracing-bridge-otel")
    implementation("io.hypersistence:hypersistence-utils-hibernate-63")
    implementation("org.mapstruct:mapstruct")

    runtimeOnly("com.mysql:mysql-connector-j")
    runtimeOnly("software.amazon.jdbc:aws-advanced-jdbc-wrapper")

    testImplementation("com.tngtech.archunit:archunit-junit5") {
        exclude(group = "junit")
    }

    integrationImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "junit")
    }
    integrationImplementation("org.springframework.cloud:spring-cloud-contract-wiremock")
    integrationImplementation("org.springframework.kafka:spring-kafka-test")
    integrationImplementation("org.springframework.boot:spring-boot-testcontainers") {
        exclude(group = "junit")
    }
    integrationImplementation("org.testcontainers:mysql")
    integrationImplementation("com.github.database-rider:rider-spring")

    kapt(group = "com.querydsl", name = "querydsl-apt", classifier="jakarta")
    kapt("org.mapstruct:mapstruct-processor")
}