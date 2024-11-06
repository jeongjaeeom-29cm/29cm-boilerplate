package kr.co._29cm.boilerplate.usecase.salescampaign

import kr.co._29cm.boilerplate.config.mapper.DefaultMapperConfig
import kr.co._29cm.boilerplate.contract.datetime.DateTimePeriod
import kr.co._29cm.boilerplate.contract.salescampaign.SalesCampaignStatus
import kr.co._29cm.boilerplate.service.salescampaign.SalesCampaignDto
import kr.co._29cm.common.contract.OffsetPage
import kr.co._29cm.common.contract.OffsetPageRequest
import org.mapstruct.Mapper

interface GetSalesCampaignUseCase {
    fun getSalesCampaign(request: GetRequest): GetSalesCampaign

    fun getSalesCampaignPage(request: GetRequestPage): OffsetPage<GetSalesCampaign>

    data class GetRequest(
        val salesCampaignId: Long,
    )

    data class GetRequestPage(
        val pageRequest: OffsetPageRequest,
    )

    data class GetSalesCampaign(
        val salesCampaignId: Long,
        val name: String,
        val status: SalesCampaignStatus,
        val period: DateTimePeriod,
        val enabled: Boolean
    )

    @Mapper(config = DefaultMapperConfig::class)
    interface GetSalesCampaignMapper {
        fun convert(salesCampaign: SalesCampaignDto): GetSalesCampaign
    }
}