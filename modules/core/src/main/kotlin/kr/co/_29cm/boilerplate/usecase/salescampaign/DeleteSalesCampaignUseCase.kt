package kr.co._29cm.boilerplate.usecase.salescampaign

interface DeleteSalesCampaignUseCase {
    fun deleteSalesCampaign(request: DeleteRequest)

    data class DeleteRequest(
        val salesCampaignId: Long,
    )
}
