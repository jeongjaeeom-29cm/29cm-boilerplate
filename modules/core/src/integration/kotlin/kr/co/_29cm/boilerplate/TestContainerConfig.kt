package kr.co._29cm.boilerplate

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.containers.Network
import org.testcontainers.containers.wait.strategy.Wait
import java.time.Duration

@TestConfiguration(proxyBeanMethods = false)
class TestContainerConfig {
    @Bean
    @ServiceConnection
    fun dbContainer(): MySQLContainer<*> {
        return MySQLContainer(DockerImages.MYSQL)
            .withDatabaseName("sales_campaign")
            .withUsername("application")
            .withPassword("application")
            .withUrlParam("serverTimezone", "Asia/Seoul")
            .withUrlParam("characterEncoding", "UTF-8")
            .waitingFor(Wait.defaultWaitStrategy())
            .withStartupTimeout(Duration.ofSeconds(120))
            .withNetwork(NETWORK)
            .withReuse(true);
    }

    companion object {
        private val NETWORK = Network.newNetwork()
    }
}