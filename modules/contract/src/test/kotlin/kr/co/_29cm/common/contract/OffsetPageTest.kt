package kr.co._29cm.common.contract

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class OffsetPageTest {
    @Test
    fun map() {
        val pagination = OffsetPagination.of(0, 10, 100)
        val offsetPage = OffsetPage(
            pagination = pagination,
            list = listOf(1, 2, 3),
        )

        val actual = offsetPage.map { it * 2 }

        Assertions.assertThat(actual.pagination).isEqualTo(pagination)
        Assertions.assertThat(actual.list).containsExactly(2, 4, 6)
    }
}