package kr.co._29cm.boilerplate.contract.salescampaign

enum class SalesCampaignStatus(
    val displayName: String,
) {
    READY("준비 중"),
    IN_PROGRESS("진행 중"),
    COMPLETED("종료"),
    PAUSED("일시 중지"),
    CANCELED("취소"),
    ;

    companion object {
        val DELETABLE_STATUSES = setOf(READY, CANCELED)
    }
}