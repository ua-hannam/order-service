package com.uahannam.order.adapter.out.kafka.event.listener

import com.uahannam.order.adapter.out.kafka.produce.dto.ModifyOrderStatusKafkaDto
import org.springframework.context.event.EventListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class ModifyOrderStatusEventListener(
    private val modifyOrderStatusKafkaTemplate: KafkaTemplate<String, ModifyOrderStatusKafkaDto>
) {

    @Async
    @EventListener(ModifyOrderStatusKafkaDto::class)
    fun handleModifyStatusEvent(modifyOrderStatusKafkaDto: ModifyOrderStatusKafkaDto) {
        modifyOrderStatusKafkaTemplate.send("modify-order-status", modifyOrderStatusKafkaDto)
    }
}