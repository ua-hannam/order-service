package com.uahannam.order.adapter.`in`.web

import com.uahannam.common.dto.BaseResponse
import com.uahannam.order.application.port.`in`.model.CreateOrderCommand
import com.uahannam.order.application.port.`in`.usecase.CreateOrderUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CreateOrderController(
    private val createOrderUseCase: CreateOrderUseCase
) {

    @PostMapping("/api/orders")
    fun createOrder(@RequestBody createOrderCommand: CreateOrderCommand) : BaseResponse<Any> {
        createOrderUseCase.createOrder(createOrderCommand)
        return BaseResponse.ok()
    }
}