package kr.co._29cm.boilerplate.usecase.salescampaign

import kr.co._29cm.boilerplate.contract.datetime.DateTimePeriod

interface CreateSalesCampaignUseCase {

    fun createSalesCampaign(request: CreateRequest): CreateResult

    data class CreateRequest(
        val name: String,
        val period: DateTimePeriod
    )

    data class CreateResult(
        val salesCampaignId: Long
    )
}