package kr.co._29cm.boilerplate.facade.salescampaign

import kr.co._29cm.boilerplate.service.salescampaign.SalesCampaignEnabler
import kr.co._29cm.boilerplate.usecase.salescampaign.EnableSalesCampaignUseCase
import org.springframework.stereotype.Service

@Service
class EnableSalesCampaignFacade(
    private val salesCampaignEnabler: SalesCampaignEnabler
) : EnableSalesCampaignUseCase {
    override fun enableSalesCampaign(request: EnableSalesCampaignUseCase.EnableRequest) {
        salesCampaignEnabler.enable(
            salesCampaignId = request.salesCampaignId
        )
    }

    override fun disableSalesCampaign(request: EnableSalesCampaignUseCase.DisableRequest) {
        salesCampaignEnabler.disable(
            salesCampaignId = request.salesCampaignId
        )
    }
}