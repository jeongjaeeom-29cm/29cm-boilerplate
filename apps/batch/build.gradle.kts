import org.springframework.boot.gradle.tasks.bundling.BootJar

apply {
    plugin("org.springframework.boot")
}

tasks.getByName<Jar>("jar") {
    enabled = false
}

tasks.getByName<BootJar>("bootJar") {
    enabled = true
}

dependencies {
    implementation(project(":modules:core"))
    implementation("org.springframework.boot:spring-boot-starter-batch")
    implementation("com.querydsl:querydsl-jpa")

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
    integrationImplementation("org.springframework.batch:spring-batch-test")
}