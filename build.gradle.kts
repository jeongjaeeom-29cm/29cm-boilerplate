import com.coditory.gradle.integration.IntegrationTestPlugin.Companion.INTEGRATION_TEST_TASK_NAME
import java.io.ByteArrayOutputStream

plugins {
    kotlin("jvm") version "2.0.20"
    kotlin("kapt") version "2.0.20"
    kotlin("plugin.spring") version "2.0.20" apply false
    kotlin("plugin.jpa") version "2.0.20" apply false
    id("idea")
    id("io.spring.dependency-management") version "1.1.6"
    id("org.springframework.boot") version "3.3.5" apply false
    id("com.coditory.integration-test") version "1.5.0" apply false
    id("com.google.cloud.tools.jib") version "3.4.4" apply false
}

group = "kr.co._29cm"
version = if (version == "unspecified") {
    val stdout = ByteArrayOutputStream()
    exec {
        commandLine = listOf("git", "rev-parse", "--short", "HEAD")
        standardOutput = stdout
    }
    stdout.toString().trim()
} else {
    version
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin("idea")
        plugin("jacoco")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.kapt")
        plugin("org.jetbrains.kotlin.plugin.spring")
        plugin("org.jetbrains.kotlin.plugin.jpa")
        plugin("io.spring.dependency-management")
        plugin("com.coditory.integration-test")
        plugin("java-test-fixtures")
    }

    group = rootProject.group
    version = rootProject.version

    kotlin {
        compilerOptions {
            freeCompilerArgs.addAll("-Xjsr305=strict")
        }
    }

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
    }

    dependencyManagement {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:2023.0.3")
            mavenBom("io.awspring.cloud:spring-cloud-aws-dependencies:3.2.0")
            mavenBom("com.querydsl:querydsl-bom:5.1.0")
        }

        dependencies {
            dependency("software.amazon.jdbc:aws-advanced-jdbc-wrapper:2.4.0")
            dependency("io.github.oshai:kotlin-logging-jvm:7.0.0")
            dependency("io.hypersistence:hypersistence-utils-hibernate-63:3.8.3")
            dependency("com.tngtech.archunit:archunit-junit5:1.3.0")
            dependency("net.logstash.logback:logstash-logback-encoder:8.0")
            dependency("org.mockito.kotlin:mockito-kotlin:3.2.0")
            dependency("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
            dependencySet("com.github.database-rider:1.44.0") {
                entry("rider-core")
                entry("rider-spring")
                entry("rider-junit5")
            }
            dependencySet("org.mapstruct:1.6.2") {
                entry("mapstruct")
                entry("mapstruct-jdk8")
                entry("mapstruct-processor")
            }
            dependencySet("io.swagger.core.v3:2.2.23") {
                entry("swagger-annotations-jakarta")
                entry("swagger-core-jakarta")
                entry("swagger-models-jakarta")
            }
        }
    }

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
        implementation("io.github.oshai:kotlin-logging-jvm")
        implementation("net.logstash.logback:logstash-logback-encoder")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
        testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")
        testImplementation("org.assertj:assertj-core")
        testImplementation("org.mockito.kotlin:mockito-kotlin")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }

    idea {
        module {
            testSources.from(sourceSets["integration"].allSource.srcDirs)
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        systemProperty("user.timezone", "Asia/Seoul")
    }

    tasks.withType<JacocoReport> {
        mustRunAfter("test", INTEGRATION_TEST_TASK_NAME)
        executionData(fileTree(layout.buildDirectory.asFile).include("jacoco/*.exec"))
        reports {
            xml.required = true
            csv.required = false
            html.required = true
        }
        afterEvaluate {
            classDirectories.setFrom(files(classDirectories.files.map {
                fileTree(it).apply {
                    exclude("kr/co/_29cm/boilerplate/entity/**/Q*.class")
                }
            }))
        }
    }
}