package kr.co._29cm.boilerplate.exception.salescampaign

import kr.co._29cm.boilerplate.exception.BusinessException

class NotFoundSalesCampaignException(
    salesCampaignId: Long
) : BusinessException(
    message = "세일즈캠페인이 존재하지 않습니다.",
    displayMessage = "세일즈캠페인[$salesCampaignId]이 존재하지 않습니다.",
    context = mapOf("salesCampaignId" to salesCampaignId)
)