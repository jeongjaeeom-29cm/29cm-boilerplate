package kr.co._29cm.boilerplate.service.salescampaign

import kr.co._29cm.boilerplate.contract.datetime.DateTimePeriod
import kr.co._29cm.boilerplate.contract.salescampaign.SalesCampaignStatus

data class SalesCampaignDto(
    val salesCampaignId: Long,
    val name: String,
    val status: SalesCampaignStatus,
    val period: DateTimePeriod,
    val enabled: Boolean,
)