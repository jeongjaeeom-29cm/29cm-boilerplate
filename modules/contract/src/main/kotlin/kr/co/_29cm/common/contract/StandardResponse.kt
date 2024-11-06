package kr.co._29cm.common.contract

import io.swagger.v3.oas.annotations.media.Schema

@Schema(
    description = "표준 응답",
    examples = [
        """
{
  "meta": {
    "result": "SUCCESS"
  },
  "data": {
    "id": 1,
    "name": "29cm"
  }
}
""",
        """
{
  "meta": {
    "result": "FAIL",
    "errorCode": "NOT_FOUND",
    "message": "데이터를 찾을 수 없습니다."
  }
}
"""
    ]
)
data class StandardResponse<T>(
    @Schema(description = "메타", required = true) val meta: Meta,
    @Schema(description = "데이터", required = false) val data: T?,
) {
    data class Meta(
        @Schema(description = "결과", required = true) val result: String,
        @Schema(description = "에러코드", required = false) val errorCode: String?,
        @Schema(description = "메시지", required = false) val message: String?,
    )

    companion object {
        object Result {
            const val SUCCESS = "SUCCESS"
            const val FAIL = "FAIL"
        }

        fun <T> success(data: T): StandardResponse<T> {
            return StandardResponse(
                meta = Meta(
                    result = Result.SUCCESS,
                    errorCode = null,
                    message = null,
                ),
                data = data,
            )
        }

        fun success(): StandardResponse<Unit> {
            return StandardResponse(
                meta = Meta(
                    result = Result.SUCCESS,
                    errorCode = null,
                    message = null,
                ),
                data = Unit,
            )
        }

        fun <T> fail(errorCode: String, message: String): StandardResponse<T> {
            return StandardResponse(
                meta = Meta(
                    result = Result.FAIL,
                    errorCode = errorCode,
                    message = message,
                ),
                data = null,
            )
        }
    }
}