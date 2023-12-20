package com.uahannam.order.repository

import com.uahannam.order.domain.entity.OrderEvent
import org.springframework.data.jpa.repository.JpaRepository
interface OrderEventRepository: JpaRepository<OrderEvent, Long> {
}