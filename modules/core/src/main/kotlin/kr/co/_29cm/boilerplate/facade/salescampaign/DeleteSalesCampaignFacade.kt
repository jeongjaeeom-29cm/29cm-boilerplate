package kr.co._29cm.boilerplate.facade.salescampaign

import kr.co._29cm.boilerplate.service.salescampaign.SalesCampaignDeleter
import kr.co._29cm.boilerplate.usecase.salescampaign.DeleteSalesCampaignUseCase
import org.springframework.stereotype.Service

@Service
class DeleteSalesCampaignFacade(
    private val salesCampaignDeleter: SalesCampaignDeleter
) : DeleteSalesCampaignUseCase {
    override fun deleteSalesCampaign(request: DeleteSalesCampaignUseCase.DeleteRequest) {
        salesCampaignDeleter.delete(
            salesCampaignId = request.salesCampaignId
        )
    }
}