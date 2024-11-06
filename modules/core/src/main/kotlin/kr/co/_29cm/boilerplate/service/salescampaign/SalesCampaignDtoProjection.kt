package kr.co._29cm.boilerplate.service.salescampaign

import com.querydsl.core.Tuple
import com.querydsl.core.types.MappingProjection
import kr.co._29cm.boilerplate.entity.salescampaign.QSalesCampaign.salesCampaign

object SalesCampaignDtoProjection : MappingProjection<SalesCampaignDto>(
    SalesCampaignDto::class.java,
    arrayOf(
        salesCampaign.id,
        salesCampaign.name,
        salesCampaign.status,
        salesCampaign.period,
        salesCampaign.enabled,
    )
) {
    private fun readResolve(): Any = SalesCampaignDtoProjection

    override fun map(tuple: Tuple): SalesCampaignDto {
        return SalesCampaignDto(
            salesCampaignId = tuple[salesCampaign.id]!!,
            name = tuple[salesCampaign.name]!!,
            status = tuple[salesCampaign.status]!!,
            period = tuple[salesCampaign.period]!!,
            enabled = tuple[salesCampaign.enabled]!!,
        )
    }
}