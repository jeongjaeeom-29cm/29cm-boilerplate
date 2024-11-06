package kr.co._29cm.common.contract

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class OffsetPageRequestTest {

    @Test
    fun calculateOffset() {
        val pageRequest = OffsetPageRequest.of(3, 10)

        Assertions.assertThat(pageRequest.calculateOffset()).isEqualTo(20)
    }
}