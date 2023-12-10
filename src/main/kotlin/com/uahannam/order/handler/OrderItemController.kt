package com.uahannam.order.handler

import com.uahannam.order.service.OrderItemService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/orderItems")
class OrderItemController(
    private val orderItemService: OrderItemService
) {

    @GetMapping
    fun findAllOrderItems() = ResponseEntity.ok().body(orderItemService.findAllOrderItems())

    @GetMapping("/{orderId}")
    fun findOrderItemsByOrderId(@PathVariable("orderId") orderId: Long) =
        ResponseEntity.ok().body(orderItemService.findAllOrderItemsByOrderId(orderId))
}