package kr.co._29cm.boilerplate.usecase.salescampaign

interface EnableSalesCampaignUseCase {
    fun enableSalesCampaign(request: EnableRequest)
    fun disableSalesCampaign(request: DisableRequest)

    data class EnableRequest(
        val salesCampaignId: Long,
    )

    data class DisableRequest(
        val salesCampaignId: Long,
    )
}