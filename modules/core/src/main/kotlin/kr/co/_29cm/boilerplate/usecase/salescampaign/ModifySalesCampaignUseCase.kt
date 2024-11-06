package kr.co._29cm.boilerplate.usecase.salescampaign

import kr.co._29cm.boilerplate.contract.datetime.DateTimePeriod

interface ModifySalesCampaignUseCase {

    fun modifySalesCampaign(request: ModifyRequest)

    data class ModifyRequest(
        val salesCampaignId: Long,
        val name: String,
        val period: DateTimePeriod
    )
}