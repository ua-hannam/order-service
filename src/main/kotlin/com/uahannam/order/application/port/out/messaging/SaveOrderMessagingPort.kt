package com.uahannam.order.application.port.out.messaging

import com.uahannam.order.adapter.out.messaging.produce.dto.SaveOrderKafkaDto

fun interface SaveOrderMessagingPort {

    fun sendSaveOrderMessage(saveOrderKafkaDto: SaveOrderKafkaDto)
}