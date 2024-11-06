package kr.co._29cm.boilerplate.config.page

import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import jakarta.validation.constraints.Min

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER)
@Parameters(
    value = [
        Parameter(
            name = "page",
            description = "페이지 (기본값: 1)",
            required = false,
            example = "1"
        ),
        Parameter(
            name = "size",
            description = "페이지 크기 (기본값: 10)",
            required = false,
            example = "10"
        )
    ]
)
annotation class InjectOffsetPageRequest(
    @Min(value = 1, message = "페이지는 1 이상 이어야 합니다.") val page: Long = 1,
    @Min(value = 1, message = "페이지 크기는 1 이상 이어야 합니다.") val size: Long = 10
)
