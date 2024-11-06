package kr.co._29cm.boilerplate

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import kotlin.system.exitProcess

@SpringBootApplication
class BoilerplateBatchApplication
fun main(args: Array<String>) {
    val exit = SpringApplication.exit(
        SpringApplicationBuilder(BoilerplateBatchApplication::class.java).run(*args)
    )
    exitProcess(exit)
}
