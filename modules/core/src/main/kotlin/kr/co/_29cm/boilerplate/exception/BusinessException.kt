package kr.co._29cm.boilerplate.exception

open class BusinessException(
    message: String,
    val displayMessage: String = "잘못된 요청입니다.",
    val context: Map<String, Any> = emptyMap(),
) : RuntimeException(message)