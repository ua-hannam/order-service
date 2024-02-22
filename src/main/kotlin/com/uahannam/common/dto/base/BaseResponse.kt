package com.uahannam.common.dto.base

import com.fasterxml.jackson.annotation.JsonInclude

data class BaseResponse<T>(
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val data: T?,
    val status: Int,
    val message: String
) {

    companion object {

        fun <T> created(data: T) = BaseResponse(
            data = data,
            status = 201,
            message = "CREATED"
        )

        fun <T> ok(data: T) = BaseResponse(
            data = data,
            status = 200,
            message = "OK"
        )

        fun ok() = BaseResponse(
            data = null,
            status = 200,
            message = "OK"
        )

    }

}