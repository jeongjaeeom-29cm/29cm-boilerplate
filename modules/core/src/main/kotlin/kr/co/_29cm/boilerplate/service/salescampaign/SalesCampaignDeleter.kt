package kr.co._29cm.boilerplate.service.salescampaign

import kr.co._29cm.boilerplate.exception.salescampaign.NotFoundSalesCampaignException
import kr.co._29cm.boilerplate.repository.salescampaign.SalesCampaignRepository
import kr.co._29cm.boilerplate.support.logging.logger
import kr.co._29cm.boilerplate.transaction.Transactions
import org.springframework.stereotype.Service

@Service
class SalesCampaignDeleter(
    private val transactions: Transactions,
    private val salesCampaignRepository: SalesCampaignRepository,
) {
    private val logger = logger()

    fun delete(
        salesCampaignId: Long,
    ) {
        logger.debug { "세일즈캠페인을 삭제합니다. salesCampaignId=$salesCampaignId" }

        transactions.inTransaction {
            val salesCampaign = salesCampaignRepository.findById(salesCampaignId).orElseThrow { NotFoundSalesCampaignException(salesCampaignId) }
            salesCampaign.validateDeletable()

            salesCampaignRepository.delete(salesCampaign)
        }
        logger.debug { "세일즈캠페인을 삭제하였습니다. salesCampaignId=$salesCampaignId" }
    }
}