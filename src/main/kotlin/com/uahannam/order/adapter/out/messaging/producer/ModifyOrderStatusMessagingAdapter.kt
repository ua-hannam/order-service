package com.uahannam.order.adapter.out.messaging.producer

import com.uahannam.common.annotation.MessagingAdapter
import com.uahannam.order.adapter.out.messaging.produce.dto.ModifyOrderStatusKafkaDto
import com.uahannam.order.application.port.out.messaging.ModifyOrderStatusMessagingPort
import org.springframework.kafka.core.KafkaTemplate

@MessagingAdapter
class ModifyOrderStatusMessagingAdapter(
    private val modifyOrderStatusKafkaTemplate: KafkaTemplate<String, ModifyOrderStatusKafkaDto>
) : ModifyOrderStatusMessagingPort {
    override fun sendModifyOrderStatusMessage(modifyOrderStatusKafkaDto: ModifyOrderStatusKafkaDto) {
        modifyOrderStatusKafkaTemplate.send("modify-order-status", modifyOrderStatusKafkaDto)
    }

}