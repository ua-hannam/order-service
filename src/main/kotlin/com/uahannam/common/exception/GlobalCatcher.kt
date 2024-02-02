package com.uahannam.common.exception

import com.uahannam.common.dto.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RestControllerAdvice
class GlobalCatcher {

    @ExceptionHandler(CustomException::class)
    protected fun handleCustomException(customException: CustomException) : ResponseEntity<ErrorResponse> {
        val errorCode = customException.errorCode

        return ResponseEntity.status(errorCode.status)
            .body(ErrorResponse(
                serial = errorCode.serial,
                message = errorCode.message,
                timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
            )
    }

    @ExceptionHandler(Exception::class)
    protected fun handleNormalException(exception: Exception) =
        ResponseEntity.status(500)
            .body(ErrorResponse(
                message = exception.toString(),
                timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
            )

}