package kr.co._29cm.boilerplate.service.salescampaign

import kr.co._29cm.boilerplate.contract.datetime.DateTimePeriod
import kr.co._29cm.boilerplate.entity.salescampaign.SalesCampaign
import kr.co._29cm.boilerplate.message.salescampaign.SalesCampaignEventMessage
import kr.co._29cm.boilerplate.message.salescampaign.SalesCampaignEventMessagePublisher
import kr.co._29cm.boilerplate.repository.salescampaign.SalesCampaignRepository
import kr.co._29cm.boilerplate.support.logging.logger
import kr.co._29cm.boilerplate.transaction.Transactions
import org.springframework.stereotype.Service

@Service
class SalesCampaignCreator(
    private val transactions: Transactions,
    private val salesCampaignRepository: SalesCampaignRepository,
    private val salesCampaignEventMessagePublisher: SalesCampaignEventMessagePublisher,
) {
    private val logger = logger()

    fun create(
        name: String,
        period: DateTimePeriod
    ): Long {
        logger.debug { "세일즈캠페인을 생성합니다. name=$name, period=$period" }

        val salesCampaign = transactions.inTransaction {
            salesCampaignRepository.save(
                SalesCampaign.create(
                    name = name,
                    period = period,
                )
            )
        }
        logger.debug { "세일즈캠페인을 생성하였습니다. salesCampaignId=${salesCampaign.id}" }

        salesCampaignEventMessagePublisher.publish(
            key = salesCampaign.id.toString(),
            message = SalesCampaignEventMessage.Created(
                id = salesCampaign.id,
            )
        )

        return salesCampaign.id
    }
}