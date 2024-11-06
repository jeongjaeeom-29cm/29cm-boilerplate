package kr.co._29cm.boilerplate.controller

import jakarta.validation.ConstraintViolationException
import kr.co._29cm.boilerplate.exception.BusinessException
import kr.co._29cm.boilerplate.exception.SystemException
import kr.co._29cm.common.contract.StandardResponse
import kr.co._29cm.boilerplate.support.logging.logger
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class BaseControllerAdvice {
    private val logger = logger()

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessException::class)
    fun businessException(e: BusinessException): StandardResponse<Unit> {
        logger.warn(e) { "비즈니스 예외가 발생하였습니다. message=${e.message}, context=${e.context}" }

        return StandardResponse.fail(
            errorCode = ErrorCode.INVALID_REQUEST.code,
            message = e.displayMessage
        )
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException::class)
    fun illegalArgumentException(e: IllegalArgumentException): StandardResponse<Unit> {
        logger.warn(e) { "잘못된 요청입니다. message=${e.message}" }

        return StandardResponse.fail(
            errorCode = ErrorCode.INVALID_REQUEST.code,
            message = e.message ?: ErrorCode.INVALID_REQUEST.message
        )
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalStateException::class)
    fun illegalStateException(e: IllegalStateException): StandardResponse<Unit> {
        logger.warn(e) { "잘못된 요청입니다. message=${e.message}" }

        return StandardResponse.fail(
            errorCode = ErrorCode.INVALID_REQUEST.code,
            message = e.message ?: ErrorCode.INVALID_REQUEST.message
        )
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(e: MethodArgumentNotValidException): StandardResponse<Unit> {
        logger.warn(e) { "잘못된 요청입니다. message=${e.message}" }

        val displayMessage = e.bindingResult.fieldErrors.firstOrNull()?.defaultMessage ?: e.message

        return StandardResponse.fail(
            errorCode = ErrorCode.INVALID_REQUEST.code,
            message = displayMessage
        )
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException::class)
    fun constraintViolationException(e: ConstraintViolationException): StandardResponse<Unit> {
        logger.warn(e) { "잘못된 요청입니다. message=${e.message}" }

        val displayMessage = e.constraintViolations.firstOrNull()?.message ?: e.message

        return StandardResponse.fail(
            errorCode = ErrorCode.INVALID_REQUEST.code,
            message = displayMessage ?: ErrorCode.INVALID_REQUEST.message
        )
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SystemException::class)
    fun systemException(e: SystemException): StandardResponse<Unit> {
        logger.error(e) { "시스템 예외가 발생하였습니다. message=${e.message}, context=${e.context}" }

        return StandardResponse.fail(
            errorCode = ErrorCode.INTERNAL_ERROR.code,
            message = e.displayMessage
        )
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun unknownException(e: Exception): StandardResponse<Unit> {
        logger.error(e) { "알 수 없는 오류가 발생하였습니다. message=${e.message}" }

        return StandardResponse.fail(
            errorCode = ErrorCode.INTERNAL_ERROR.code,
            message = ErrorCode.INTERNAL_ERROR.message
        )
    }
}