package com.uahannam.common.dto.base

import com.fasterxml.jackson.annotation.JsonInclude

class ErrorResponse(
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val serial: String? = null,
    val message: String,
    val timestamp: String
)