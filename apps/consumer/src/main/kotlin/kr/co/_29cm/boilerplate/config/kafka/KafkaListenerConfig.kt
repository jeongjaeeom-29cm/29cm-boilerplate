package kr.co._29cm.boilerplate.config.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.annotation.KafkaListenerConfigurer
import org.springframework.kafka.config.KafkaListenerEndpointRegistrar
import org.springframework.kafka.support.converter.ByteArrayJsonMessageConverter
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean

@EnableKafka
@Configuration
class KafkaListenerConfig(
    private val validatorFactoryBean: LocalValidatorFactoryBean,
) : KafkaListenerConfigurer {
    override fun configureKafkaListeners(registrar: KafkaListenerEndpointRegistrar) {
        registrar.setValidator(validatorFactoryBean)
    }

    @Bean
    fun messageConverter(objectMapper: ObjectMapper) = ByteArrayJsonMessageConverter(objectMapper)
}