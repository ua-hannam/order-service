package com.uahannam.order.repository

import com.uahannam.order.domain.entity.OrderItem
import org.springframework.data.jpa.repository.JpaRepository

interface OrderItemRepository: JpaRepository<OrderItem, Long> {

    fun findByOrderId(orderId: Long) : List<OrderItem>
}