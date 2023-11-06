package com.uahannam.order.repository

import com.uahannam.order.domain.entity.OrderEvent
import org.springframework.data.r2dbc.repository.R2dbcRepository

interface OrderEventRepository: R2dbcRepository<OrderEvent, Long> {
}