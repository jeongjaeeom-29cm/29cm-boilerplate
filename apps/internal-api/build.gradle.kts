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
        mainClass = "kr.co._29cm.boilerplate.BoilerplateApiApplicationKt"
        environment = mapOf(
            "TZ" to "Asia/Seoul"
        )
        ports = listOf("8080")
    }
}

dependencies {
    implementation(project(":modules:core"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.data:spring-data-commons")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui")
    implementation("io.micrometer:micrometer-registry-prometheus")

    testImplementation("com.tngtech.archunit:archunit-junit5") {
        exclude(group = "junit")
    }

    integrationImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "junit")
    }
}