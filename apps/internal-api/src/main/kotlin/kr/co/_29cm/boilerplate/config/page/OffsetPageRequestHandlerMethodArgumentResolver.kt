package kr.co._29cm.boilerplate.config.page

import kr.co._29cm.common.contract.OffsetPageRequest
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

class OffsetPageRequestHandlerMethodArgumentResolver : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(InjectOffsetPageRequest::class.java)
                && OffsetPageRequest::class.java.isAssignableFrom(parameter.parameterType)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): OffsetPageRequest {
        val annotation = parameter.getParameterAnnotation(InjectOffsetPageRequest::class.java)
            ?: throw IllegalStateException("InjectOffsetPageRequest annotation not found")

        val page = webRequest.getNumberParameterOrNull("page")?.takeIf { it > 0 } ?: annotation.page
        val size = webRequest.getNumberParameterOrNull("size")?.takeIf { it > 0 } ?: annotation.size

        return OffsetPageRequest.of(
            page = page,
            size = size,
        )
    }

    private fun NativeWebRequest.getNumberParameterOrNull(name: String): Long? {
        return try {
            getParameter(name)?.toLong()
        } catch (e: Exception) {
            null
        }
    }
}