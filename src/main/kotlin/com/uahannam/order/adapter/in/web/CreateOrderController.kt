package com.uahannam.order.adapter.`in`.web

import com.uahannam.order.application.port.`in`.model.CreateOrderCommand
import com.uahannam.order.application.port.`in`.usecase.CreateOrderUseCase
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CreateOrderController(
    private val createOrderUseCase: CreateOrderUseCase
) {

    @PostMapping("/api/orders")
    fun createOrder(@RequestBody createOrderCommand: CreateOrderCommand) : ResponseEntity<Long> {
        val orderId = createOrderUseCase.createOrder(createOrderCommand)
        return ResponseEntity.status(CREATED)
            .body(orderId)
    }
}