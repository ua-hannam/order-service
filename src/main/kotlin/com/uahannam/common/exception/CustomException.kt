package com.uahannam.common.exception

class CustomException(
    val errorCode: ErrorCode
) : RuntimeException()