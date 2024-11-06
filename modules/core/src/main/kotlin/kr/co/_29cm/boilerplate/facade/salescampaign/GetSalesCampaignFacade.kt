package kr.co._29cm.boilerplate.facade.salescampaign

import kr.co._29cm.boilerplate.service.salescampaign.SalesCampaignReader
import kr.co._29cm.boilerplate.usecase.salescampaign.GetSalesCampaignUseCase
import kr.co._29cm.common.contract.OffsetPage
import org.springframework.stereotype.Service

@Service
class GetSalesCampaignFacade(
    private val salesCampaignReader: SalesCampaignReader,
    private val getSalesCampaignMapper: GetSalesCampaignUseCase.GetSalesCampaignMapper,
) : GetSalesCampaignUseCase {
    override fun getSalesCampaign(request: GetSalesCampaignUseCase.GetRequest): GetSalesCampaignUseCase.GetSalesCampaign {
        val salesCampaign = salesCampaignReader.getSalesCampaign(request.salesCampaignId)
        return getSalesCampaignMapper.convert(salesCampaign)
    }

    override fun getSalesCampaignPage(request: GetSalesCampaignUseCase.GetRequestPage): OffsetPage<GetSalesCampaignUseCase.GetSalesCampaign> {
        val salesCampaignPage = salesCampaignReader.getSalesCampaignPage(request.pageRequest)
        return salesCampaignPage.map { getSalesCampaignMapper.convert(it) }
    }
}