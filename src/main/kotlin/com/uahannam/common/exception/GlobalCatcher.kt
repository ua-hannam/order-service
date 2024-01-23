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
                message = "처리 중 오류가 발생했습니다. 확인 후 다시 시도해주시기 바랍니다.",
                timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
            )

}