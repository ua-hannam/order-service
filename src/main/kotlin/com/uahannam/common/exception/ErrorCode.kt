package com.uahannam.common.exception

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.*

enum class ErrorCode(
    val status: HttpStatus,
    val serial: String,
    val message: String
) {

    ORDER_NOT_FOUND(BAD_REQUEST, "ERROR_01", "해당하는 주문이 없습니다. 확인 후 다시 시도해주시기 바랍니다.")
}