package com.uahannam.order.adapter.out.kafka.event.listener

import com.uahannam.order.adapter.out.kafka.produce.dto.ModifyOrderStatusKafkaDto
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class ModifyOrderStatusEventListener(
    private val modifyOrderStatusKafkaTemplate: KafkaTemplate<String, ModifyOrderStatusKafkaDto>
) {

    @Async
    @TransactionalEventListener(ModifyOrderStatusKafkaDto::class)
    fun handleModifyStatusEvent(modifyOrderStatusKafkaDto: ModifyOrderStatusKafkaDto) {
        modifyOrderStatusKafkaTemplate.send("modify-order-status", modifyOrderStatusKafkaDto)
    }
}