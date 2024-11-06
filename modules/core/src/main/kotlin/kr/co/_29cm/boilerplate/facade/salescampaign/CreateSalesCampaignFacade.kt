package kr.co._29cm.boilerplate.facade.salescampaign

import kr.co._29cm.boilerplate.service.salescampaign.SalesCampaignCreator
import kr.co._29cm.boilerplate.usecase.salescampaign.CreateSalesCampaignUseCase
import org.springframework.stereotype.Service

@Service
class CreateSalesCampaignFacade(
    private val salesCampaignCreator: SalesCampaignCreator
) : CreateSalesCampaignUseCase {
    override fun createSalesCampaign(request: CreateSalesCampaignUseCase.CreateRequest): CreateSalesCampaignUseCase.CreateResult {
        val salesCampaignId = salesCampaignCreator.create(
            name = request.name,
            period = request.period
        )

        return CreateSalesCampaignUseCase.CreateResult(
            salesCampaignId = salesCampaignId
        )
    }
}