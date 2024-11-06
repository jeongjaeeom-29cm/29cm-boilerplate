package kr.co._29cm.boilerplate.support.querydsl

import com.querydsl.jpa.JPQLQuery
import kr.co._29cm.common.contract.OffsetPage
import kr.co._29cm.common.contract.OffsetPageRequest
import kr.co._29cm.common.contract.OffsetPagination

fun <T> JPQLQuery<T>.applyPageRequest(pageRequest: OffsetPageRequest): JPQLQuery<T> {
    return this.apply {
        offset(pageRequest.calculateOffset())
        limit(pageRequest.size)
    }
}

fun <T> JPQLQuery<T>.fetchPage(pageRequest: OffsetPageRequest): OffsetPage<T> {
    val totalCount = this.fetchCount()
    val content = this.applyPageRequest(pageRequest).fetch()

    return OffsetPage(
        pagination = OffsetPagination.of(
            page = pageRequest.page,
            size = pageRequest.size,
            totalCount = totalCount,
        ),
        list = content,
    )
}