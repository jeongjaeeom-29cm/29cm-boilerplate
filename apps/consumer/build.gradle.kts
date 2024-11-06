import org.springframework.boot.gradle.tasks.bundling.BootJar

apply {
    plugin("org.springframework.boot")
    plugin("com.google.cloud.tools.jib")
}

tasks.getByName<Jar>("jar") {
    enabled = false
}

tasks.getByName<BootJar>("bootJar") {
    enabled = true
}

configure<com.google.cloud.tools.jib.gradle.JibExtension> {
    from {
        image = "public.ecr.aws/amazoncorretto/amazoncorretto:21"
    }
    container {
        mainClass = "kr.co._29cm.boilerplate.BoilerplateConsumerApplicationKt"
        environment = mapOf(
            "TZ" to "Asia/Seoul"
        )
        ports = listOf("8080")
    }
}

dependencies {
    implementation(project(":modules:core"))
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.kafka:spring-kafka")

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
}