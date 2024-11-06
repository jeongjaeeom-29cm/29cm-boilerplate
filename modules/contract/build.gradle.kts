tasks.getByName<Jar>("jar") {
    enabled = true
}

dependencies {
    implementation("jakarta.validation:jakarta.validation-api")
    implementation("io.swagger.core.v3:swagger-annotations-jakarta")
}