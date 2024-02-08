package com.uahannam.common.util

import com.uahannam.common.exception.CustomException
import com.uahannam.common.exception.ErrorCode.*
import org.springframework.util.CollectionUtils
import org.springframework.util.StringUtils

fun requireLongValue(value: Long?) {
    if (value == null || value == 0L || value < 0L)
        throwCustomException()
}

fun requireIntValue(value: Int?) {
    if (value == null || value == 0 || value < 0)
        throwCustomException()
}

fun requireStringValue(value: String?) {
    if (!StringUtils.hasText(value))
        throwCustomException()
}

fun <T> requireCollection(value: Collection<T>) {
    if (CollectionUtils.isEmpty(value))
        throwCustomException()
}

private fun throwCustomException(): Nothing = throw CustomException(IS_NOT_CORRECTLY_VALUE)