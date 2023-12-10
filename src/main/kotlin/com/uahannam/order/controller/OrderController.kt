package com.uahannam.order.controller

import com.uahannam.order.domain.request.OrderCreateRequestDto
import com.uahannam.order.domain.request.OrderModifyRequestDto
import com.uahannam.order.service.OrderService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/orders")
class OrderController(
        private val orderService: OrderService
) {


    @PostMapping
    fun saveOrder(@RequestBody orderCreateRequestDto: OrderCreateRequestDto) : ResponseEntity<HttpStatus> {
        orderService.saveOrder(orderCreateRequestDto, 1L)
        return ResponseEntity.ok().build()
    }

    @GetMapping
    fun findAllOrders() = ResponseEntity.ok().body(orderService.findAllOrders())

    @PatchMapping("/{orderId}")
    fun modifyOrderStatusByOrderId(@RequestBody orderModifyRequestDto: OrderModifyRequestDto,
                                   @PathVariable("orderId") orderId: Long) : ResponseEntity<HttpStatus> {
        orderService.modifyOrderStatus(orderModifyRequestDto.orderStatus, orderId)
        return ResponseEntity.ok().build()
    }

}