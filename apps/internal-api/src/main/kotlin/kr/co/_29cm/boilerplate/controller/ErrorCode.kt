package kr.co._29cm.boilerplate.controller

enum class ErrorCode(
    val code: String,
    val message: String,
) {
    INVALID_REQUEST("INVALID_REQUEST", "잘못된 요청입니다."),
    INTERNAL_ERROR("INTERNAL_ERROR", "알 수 없는 오류가 발생하였습니다."),
    ;
}