package kr.co._29cm.common.contract

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min

/**
 * 오프셋 기반 페이지 요청
 * https://wiki.musinsa.com/pages/viewpage.action?pageId=193166186
 */
@Schema(description = "오프셋 기반 페이지 요청")
data class OffsetPageRequest(
    @Min(value = 1, message = "페이지는 1 이상 이어야 합니다.") val page: Long,
    @Min(value = 1, message = "페이지크기는 1 이상 이어야 합니다.") val size: Long,
) {
    fun calculateOffset(): Long {
        return (page - 1) * size
    }

    companion object {
        fun of(page: Long, size: Long): OffsetPageRequest {
            return OffsetPageRequest(
                page = page,
                size = size,
            )
        }
    }
}