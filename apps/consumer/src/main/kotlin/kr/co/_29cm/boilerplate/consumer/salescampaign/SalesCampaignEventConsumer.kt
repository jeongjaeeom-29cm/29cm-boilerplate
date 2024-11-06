package kr.co._29cm.boilerplate.consumer.salescampaign
import kr.co._29cm.boilerplate.consumer.DefaultConsumer
import kr.co._29cm.boilerplate.message.salescampaign.SalesCampaignEventMessage
import kr.co._29cm.boilerplate.support.logging.logger
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.annotation.RetryableTopic
import org.springframework.kafka.retrytopic.SameIntervalTopicReuseStrategy
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.retry.annotation.Backoff
import org.springframework.stereotype.Component

@Component
class SalesCampaignEventConsumer {
    private val logger = logger()

    @RetryableTopic(
        attempts = "3",
        backoff = Backoff(delay = 1000),
        autoCreateTopics = "true",
        sameIntervalTopicReuseStrategy = SameIntervalTopicReuseStrategy.SINGLE_TOPIC,
        retryTopicSuffix = ".$GROUP_ID-retry",
        dltTopicSuffix = ".$GROUP_ID-dlt"
    )
    @KafkaListener(
        topics = ["\${consumer.sales-campaign-event.topic}"],
        groupId = GROUP_ID
    )
    fun receive(@Payload message: SalesCampaignEventMessage) {
        logger.info { "[SalesCampaignEventConsumer] Receive SalesCampaignEventMessage. message=$message" }
    }

    companion object {
        const val GROUP_ID = DefaultConsumer.GROUP_ID
    }
}