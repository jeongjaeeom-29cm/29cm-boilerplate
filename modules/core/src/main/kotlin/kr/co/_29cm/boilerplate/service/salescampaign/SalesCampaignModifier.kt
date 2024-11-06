package kr.co._29cm.boilerplate.service.salescampaign

import kr.co._29cm.boilerplate.contract.datetime.DateTimePeriod
import kr.co._29cm.boilerplate.exception.salescampaign.NotFoundSalesCampaignException
import kr.co._29cm.boilerplate.repository.salescampaign.SalesCampaignRepository
import kr.co._29cm.boilerplate.support.logging.logger
import kr.co._29cm.boilerplate.transaction.Transactions
import org.springframework.stereotype.Service

@Service
class SalesCampaignModifier(
    private val transactions: Transactions,
    private val salesCampaignRepository: SalesCampaignRepository,
) {
    private val logger = logger()

    fun modify(
        salesCampaignId: Long,
        name: String,
        period: DateTimePeriod
    ) {
        logger.debug { "세일즈캠페인을 수정합니다. salesCampaignId=$salesCampaignId, name=$name, period=$period" }

        transactions.inTransaction {
            val salesCampaign = salesCampaignRepository.findById(salesCampaignId).orElseThrow { NotFoundSalesCampaignException(salesCampaignId) }

            salesCampaign.modify(
                name = name,
                period = period,
            )
        }
        logger.debug { "세일즈캠페인을 수정하였습니다. salesCampaignId=$salesCampaignId" }
    }
}