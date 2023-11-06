package com.uahannam.order.repository

import com.uahannam.order.domain.entity.OrderItem
import org.springframework.data.r2dbc.repository.R2dbcRepository

interface OrderItemRepository: R2dbcRepository<OrderItem, Long> {
}