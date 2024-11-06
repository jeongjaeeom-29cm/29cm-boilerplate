package kr.co._29cm.boilerplate.message.salescampaign

import kr.co._29cm.boilerplate.message.Message
import kr.co._29cm.boilerplate.message.MessagePublisher
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class SalesCampaignEventMessagePublisher(
    private val kafkaTemplate: KafkaTemplate<String, Message>,
    @Value("\${publisher.boilerplate-event.topic}") private val topic: String,
) : MessagePublisher<SalesCampaignEventMessage> {

    override fun publish(key: String, message: SalesCampaignEventMessage) {
        kafkaTemplate.send(topic, key, message)
    }
}