package com.uahannam.order.repository

import com.uahannam.order.domain.entity.OrderItem
import org.springframework.data.r2dbc.repository.R2dbcRepository
import reactor.core.publisher.Flux

interface OrderItemRepository: R2dbcRepository<OrderItem, Long> {

    fun findByOrderId(orderId: Long) : Flux<OrderItem>
}