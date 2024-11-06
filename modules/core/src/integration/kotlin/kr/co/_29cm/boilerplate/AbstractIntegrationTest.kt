package kr.co._29cm.boilerplate

import com.github.database.rider.spring.api.DBRider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.kafka.test.EmbeddedKafkaBroker
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.context.jdbc.Sql

@DBRider
@Sql(scripts = ["classpath:datasets/clean.sql"], executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@AutoConfigureWireMock(port = 0)
@EmbeddedKafka
@SpringBootTest(
    classes = [
        TestContainerConfig::class,
    ]
)
abstract class AbstractIntegrationTest {
    @Autowired
    protected lateinit var embeddedKafka: EmbeddedKafkaBroker
}
