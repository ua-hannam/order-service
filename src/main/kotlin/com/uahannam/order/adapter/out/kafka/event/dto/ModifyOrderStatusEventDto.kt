package com.uahannam.order.adapter.out.kafka.event.dto

import com.uahannam.order.domain.OrderStatus

data class ModifyOrderStatusEventDto(
    val orderId: Long,
    val orderStatus: OrderStatus
)