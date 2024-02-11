package com.uahannam.common.event.listener

import com.uahannam.order.adapter.out.messaging.produce.dto.SaveOrderKafkaDto
import com.uahannam.order.adapter.out.messaging.producer.SaveOrderMessagingAdapter
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class SaveOrderEventListener(
    private val saveOrderMessagingAdapter: SaveOrderMessagingAdapter
) {

    @Async
    @TransactionalEventListener(SaveOrderKafkaDto::class)
    fun handleSaveEvent(saveOrderKafkaDto: SaveOrderKafkaDto) {

        // ReadModel 이벤트 발행 -> 비동기 처리
        saveOrderMessagingAdapter.sendSaveOrderMessage(saveOrderKafkaDto)

    }
}