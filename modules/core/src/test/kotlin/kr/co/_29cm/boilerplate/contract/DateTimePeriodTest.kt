package kr.co._29cm.boilerplate.contract

import kr.co._29cm.common.contract.DateTimeContract
import kr.co._29cm.boilerplate.contract.datetime.DateTimePeriod
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class DateTimePeriodTest {

    @Test
    fun contains() {
        val base = DateTimePeriod(
            startAt = LocalDateTime.of(2024, 10, 1, 10, 0).atZone(DateTimeContract.ZONE_SEOUL),
            endAt = LocalDateTime.of(2024, 10, 3, 0, 0).atZone(DateTimeContract.ZONE_SEOUL),
        )

        assertThat(base.contains(
            DateTimePeriod(
            startAt = LocalDateTime.of(2024, 10, 1, 10, 0).atZone(DateTimeContract.ZONE_SEOUL),
            endAt = LocalDateTime.of(2024, 10, 3, 0, 0).atZone(DateTimeContract.ZONE_SEOUL),
        )
        )).isTrue()
    }
}