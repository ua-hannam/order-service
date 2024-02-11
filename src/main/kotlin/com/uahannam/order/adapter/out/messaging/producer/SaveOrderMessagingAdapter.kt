package com.uahannam.order.adapter.out.messaging.producer

import com.uahannam.common.annotation.MessagingAdapter
import com.uahannam.order.adapter.out.messaging.produce.dto.SaveOrderKafkaDto
import com.uahannam.order.application.port.out.messaging.SaveOrderMessagingPort
import org.springframework.kafka.core.KafkaTemplate

@MessagingAdapter
class SaveOrderMessagingAdapter(
    private val saveOrderKafkaTemplate: KafkaTemplate<String, SaveOrderKafkaDto>
) : SaveOrderMessagingPort {

    override fun sendSaveOrderMessage(saveOrderKafkaDto: SaveOrderKafkaDto) {
        saveOrderKafkaTemplate.send("save-order-data", saveOrderKafkaDto)
    }

}