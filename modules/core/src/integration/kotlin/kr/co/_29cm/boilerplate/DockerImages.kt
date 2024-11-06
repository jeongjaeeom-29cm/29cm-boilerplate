package kr.co._29cm.boilerplate

import org.testcontainers.utility.DockerImageName

object DockerImages {
    val MYSQL = DockerImageName.parse("public.ecr.aws/bitnami/mysql")
        .withTag("5.7.43")
        .asCompatibleSubstituteFor("mysql")
}