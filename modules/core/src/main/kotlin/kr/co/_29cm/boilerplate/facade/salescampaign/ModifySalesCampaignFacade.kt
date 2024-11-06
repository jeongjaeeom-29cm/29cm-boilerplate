package kr.co._29cm.boilerplate.facade.salescampaign

import kr.co._29cm.boilerplate.service.salescampaign.SalesCampaignModifier
import kr.co._29cm.boilerplate.usecase.salescampaign.ModifySalesCampaignUseCase
import org.springframework.stereotype.Service

@Service
class ModifySalesCampaignFacade(
    private val salesCampaignModifier: SalesCampaignModifier,
) : ModifySalesCampaignUseCase {

    override fun modifySalesCampaign(request: ModifySalesCampaignUseCase.ModifyRequest) {
        salesCampaignModifier.modify(
            salesCampaignId = request.salesCampaignId,
            name = request.name,
            period = request.period
        )
    }
}
