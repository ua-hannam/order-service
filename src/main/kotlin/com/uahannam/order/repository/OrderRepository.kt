package com.uahannam.order.repository


import com.uahannam.order.domain.entity.Order
import org.springframework.data.r2dbc.repository.R2dbcRepository

interface OrderRepository: R2dbcRepository<Order, Long> {
}