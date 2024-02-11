package com.uahannam.order.application.port.out.messaging

import com.uahannam.order.adapter.out.messaging.produce.dto.ModifyOrderStatusKafkaDto

fun interface ModifyOrderStatusMessagingPort {
    fun sendModifyOrderStatusMessage(modifyOrderStatusKafkaDto: ModifyOrderStatusKafkaDto)
}