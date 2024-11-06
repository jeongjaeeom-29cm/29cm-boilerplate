package kr.co._29cm.boilerplate.message.salescampaign

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import kr.co._29cm.boilerplate.message.Message

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes(
    JsonSubTypes.Type(
        value = SalesCampaignEventMessage.Created::class,
        name = SalesCampaignEventMessage.Created.TYPE
    )
)
sealed interface SalesCampaignEventMessage: Message {
    val id: Long

    data class Created(
        override val id: Long,
    ) : SalesCampaignEventMessage {
        companion object {
            const val TYPE = "CREATED"
        }
    }
}