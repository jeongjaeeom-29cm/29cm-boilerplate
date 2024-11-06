package kr.co._29cm.common.contract

import io.swagger.v3.oas.annotations.media.Schema

/**
 * 오프셋 기반 페이지 모델
 * https://wiki.musinsa.com/pages/viewpage.action?pageId=193166186
 */
@Schema(description = "오프셋 기반 모델")
data class OffsetPage<T>(
    val pagination: OffsetPagination,
    val list: List<T>,
) {
    fun <R> map(transform: (T) -> R): OffsetPage<R> {
        return OffsetPage(
            pagination = pagination,
            list = list.map(transform),
        )
    }
}