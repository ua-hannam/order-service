package com.uahannam.order.adapter.out.kafka.event.dto

import com.uahannam.order.adapter.out.persistence.entity.OrderItemJpaEntity
import com.uahannam.order.adapter.out.persistence.entity.OrderJpaEntity

data class SaveOrderEventDto(
    val orderJpaEntity: OrderJpaEntity,
    val orderItemJpaEntityList: List<OrderItemJpaEntity>
)