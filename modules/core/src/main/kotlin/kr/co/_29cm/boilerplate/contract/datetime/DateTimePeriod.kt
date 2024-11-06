package kr.co._29cm.boilerplate.contract.datetime

import jakarta.persistence.Embeddable
import kr.co._29cm.boilerplate.support.datetime.isAfterOrEqual
import kr.co._29cm.boilerplate.support.datetime.isBeforeOrEqual
import java.time.ZonedDateTime

@Embeddable
data class DateTimePeriod(
    val startAt: ZonedDateTime,
    val endAt: ZonedDateTime,
) {
    fun contains(other: DateTimePeriod): Boolean {
        return this.startAt.isBeforeOrEqual(other.startAt) && this.endAt.isAfterOrEqual(other.endAt)
    }

    init {
        require(startAt.isBefore(endAt)) { "시작 시간은 종료 시간보다 이전이어야 합니다." }
    }
}
