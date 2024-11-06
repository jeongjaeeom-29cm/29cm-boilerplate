package kr.co._29cm.boilerplate.service.salescampaign

import kr.co._29cm.boilerplate.exception.salescampaign.NotFoundSalesCampaignException
import kr.co._29cm.boilerplate.repository.salescampaign.SalesCampaignRepository
import kr.co._29cm.boilerplate.support.logging.logger
import kr.co._29cm.boilerplate.transaction.Transactions
import org.springframework.stereotype.Service

@Service
class SalesCampaignEnabler(
    private val transactions: Transactions,
    private val salesCampaignRepository: SalesCampaignRepository,
) {
    private val logger = logger()

    fun enable(
        salesCampaignId: Long,
    ) {
        logger.debug { "세일즈캠페인을 활성화합니다. salesCampaignId=$salesCampaignId" }

        transactions.inTransaction {
            val salesCampaign = salesCampaignRepository.findById(salesCampaignId).orElseThrow { NotFoundSalesCampaignException(salesCampaignId) }

            salesCampaign.enable()
        }
        logger.debug { "세일즈캠페인을 활성화하였습니다. salesCampaignId=$salesCampaignId" }
    }

    fun disable(
        salesCampaignId: Long,
    ) {
        logger.debug { "세일즈캠페인을 비활성화합니다. salesCampaignId=$salesCampaignId" }

        transactions.inTransaction {
            val salesCampaign = salesCampaignRepository.findById(salesCampaignId).orElseThrow { NotFoundSalesCampaignException(salesCampaignId) }

            salesCampaign.disable()
        }
        logger.debug { "세일즈캠페인을 비활성화하였습니다. salesCampaignId=$salesCampaignId" }
    }
}