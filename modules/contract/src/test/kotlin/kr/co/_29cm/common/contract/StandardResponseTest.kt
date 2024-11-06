package kr.co._29cm.common.contract

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StandardResponseTest {
    @Test
    fun success() {
        val response = StandardResponse.success("payload")

        assertThat(response.meta.result).isEqualTo("SUCCESS")
        assertThat(response.data).isEqualTo("payload")
    }

    @Test
    fun success_void() {
        val response = StandardResponse.success()

        assertThat(response.meta.result).isEqualTo("SUCCESS")
    }

    @Test
    fun fail() {
        val response = StandardResponse.fail<Unit>("NOT_FOUND", "데이터를 찾을 수 없습니다.")

        assertThat(response.meta.result).isEqualTo("FAIL")
        assertThat(response.meta.errorCode).isEqualTo("NOT_FOUND")
        assertThat(response.meta.message).isEqualTo("데이터를 찾을 수 없습니다.")
    }
}