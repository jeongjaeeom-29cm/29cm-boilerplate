package kr.co._29cm.boilerplate.exception

open class SystemException(
    message: String,
    val displayMessage: String = "알 수 없는 오류가 발생하였습니다.",
    val context: Map<String, Any> = emptyMap(),
) : RuntimeException(message)