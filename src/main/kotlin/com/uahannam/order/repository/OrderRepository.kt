package com.uahannam.order.repository


import com.uahannam.order.domain.entity.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<Order, Long> {
}