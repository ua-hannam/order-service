package com.uahannam.order.adapter.`in`.web

import com.uahannam.common.dto.base.BaseResponse
import com.uahannam.order.application.port.`in`.usecase.ModifyOrderStatusUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ModifyOrderStatusController(
    private val modifyOrderStatusUseCase: ModifyOrderStatusUseCase
) {

    @PatchMapping("/api/orders/{orderId}")
    fun modifyOrderStatus(@PathVariable("orderId") orderId: Long) =
        ResponseEntity.ok(
            BaseResponse.ok(modifyOrderStatusUseCase.modifyOrderStatus(orderId))
        )
}