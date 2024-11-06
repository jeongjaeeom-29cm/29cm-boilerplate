package kr.co._29cm.common.contract

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min

/**
 * 오프셋 기반 페이지네이션
 * https://wiki.musinsa.com/pages/viewpage.action?pageId=193166186
 */
@Schema(
    description = "오프셋 기반 페이지네이션",
    example = """
{
  "page": 1,
  "size": 10,
  "totalCount": 100,
  "hasNext": true,
  "totalPages": 10
}
"""
)
data class OffsetPagination(
    @Min(value = 1, message = "페이지는 1 이상 이어야 합니다.") val page: Long,
    @Min(value = 1, message = "페이지크기는 1 이상 이어야 합니다.") val size: Long,
    @Min(value = 0, message = "전체 카운트는 0 이상 이어야 합니다.") val totalCount: Long,
    val hasNext: Boolean,
    @Min(value = 0, message = "전체 페이지는 0 이상 이어야 합니다.") val totalPages: Long,
) {
    companion object {
        fun of(page: Long, size: Long, totalCount: Long): OffsetPagination {
            val totalPages = if (totalCount % size == 0L) {
                totalCount / size
            } else {
                (totalCount / size) + 1
            }
            return OffsetPagination(
                page = page,
                size = size,
                totalCount = totalCount,
                hasNext = page < totalPages,
                totalPages = totalPages,
            )
        }
    }
}