package com.uahannam.order.adapter.`in`.web

import com.uahannam.common.dto.BaseResponse
import com.uahannam.order.application.port.`in`.usecase.ModifyOrderUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ModifyOrderController(
    private val modifyOrderUseCase: ModifyOrderUseCase
) {

    @PatchMapping("/api/orders/{orderId}")
    fun modifyOrderStatus(@PathVariable("orderId") orderId: Long) =
        ResponseEntity.ok(
            BaseResponse(modifyOrderUseCase.modifyOrderStatus(orderId))
        )
}