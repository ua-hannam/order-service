package com.uahannam.common.dto

import com.fasterxml.jackson.annotation.JsonInclude

class BaseResponse<T>(
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val data: T?,
    val status: Int
) {

    companion object {
        fun <T> ok(data: T) = BaseResponse(data, 200)
        fun ok(): BaseResponse<Any> = BaseResponse(null, 200)
    }
}