package com.uahannam.order.adapter.out.messaging.produce.dto

import com.uahannam.order.domain.OrderStatus

data class ModifyOrderStatusKafkaDto(
    val modifyOrderStatusEventDto: ModifyOrderStatusEventDto,
    val orderEvent: OrderEvent,
)

data class ModifyOrderStatusEventDto(
    val orderId: Long,
    val orderStatus: OrderStatus
)