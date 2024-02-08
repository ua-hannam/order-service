package com.uahannam.common.exception

import com.uahannam.common.dto.ErrorResponse
import com.uahannam.common.util.CurrentTimeGenerator
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.Exception

@RestControllerAdvice
class GlobalCatcher {

    private val logger = LoggerFactory.getLogger(GlobalCatcher::class.java)

    @ExceptionHandler(CustomException::class)
    protected fun handleCustomException(customException: CustomException) : ResponseEntity<ErrorResponse> {
        val errorCode = customException.errorCode
        val issuedTime = CurrentTimeGenerator.generateCurrentTime()

        logger.error("주문 서비스 커스텀 예외 발생 => {}", errorCode.message)
        logger.error("예외 발생 시각 => {}", issuedTime)

        return ResponseEntity.status(errorCode.status)
            .body(ErrorResponse(
                serial = errorCode.serial,
                message = errorCode.message,
                timestamp = issuedTime)
            )
    }

    @ExceptionHandler(Exception::class)
    protected fun handleNormalException(exception: Exception) : ResponseEntity<ErrorResponse> {
        val issuedTime = CurrentTimeGenerator.generateCurrentTime()

        logger.error("주문 서비스 일반 예외 발생 => {}", exception.toString())
        logger.error("예외 발생 시각 => {}", issuedTime)

        return ResponseEntity.status(500)
            .body(ErrorResponse(
                message = exception.toString(),
                timestamp = issuedTime)
            )
    }


}