package com.uahannam.order.adapter.out.kafka.produce.dto

import com.uahannam.order.adapter.out.kafka.event.dto.ModifyOrderStatusEventDto

data class ModifyOrderStatusKafkaDto(
    val modifyOrderStatusEventDto: ModifyOrderStatusEventDto,
    val orderEvent: OrderEvent,
)