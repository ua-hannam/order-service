package com.uahannam.common.event.listener

import com.uahannam.order.adapter.out.messaging.produce.dto.ModifyOrderStatusKafkaDto
import com.uahannam.order.adapter.out.messaging.producer.ModifyOrderStatusMessagingAdapter
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class ModifyOrderStatusEventListener(
    private val modifyOrderStatusMessagingAdapter: ModifyOrderStatusMessagingAdapter
) {

    @Async
    @TransactionalEventListener(ModifyOrderStatusKafkaDto::class)
    fun handleModifyStatusEvent(modifyOrderStatusKafkaDto: ModifyOrderStatusKafkaDto) {
        modifyOrderStatusMessagingAdapter.sendModifyOrderStatusMessage(modifyOrderStatusKafkaDto)
    }
}