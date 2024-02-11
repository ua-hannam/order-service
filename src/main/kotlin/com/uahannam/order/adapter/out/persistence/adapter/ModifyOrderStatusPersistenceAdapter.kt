package com.uahannam.order.adapter.out.persistence.adapter

import com.uahannam.common.annotation.PersistenceAdapter
import com.uahannam.common.util.EventProducer
import com.uahannam.order.adapter.out.messaging.produce.dto.ModifyOrderStatusEventDto
import com.uahannam.order.adapter.out.messaging.produce.dto.ModifyOrderStatusKafkaDto
import com.uahannam.order.adapter.out.messaging.produce.dto.OrderEvent
import com.uahannam.order.adapter.out.persistence.repository.OrderRepository
import com.uahannam.order.application.port.out.persistence.ModifyOrderStatusPort
import com.uahannam.order.domain.Order
import java.util.UUID

@PersistenceAdapter
class ModifyOrderStatusPersistenceAdapter(
    private val orderRepository: OrderRepository
) : ModifyOrderStatusPort {


    override fun modifyOrderStatus(order: Order): Long {
        orderRepository.modifyOrderStatus(order.orderInfo.orderStatus, order.orderInfo.orderId)

        EventProducer.produceEvent(
            createModifyOrderStatusKafkaDto(order)
        )

        return order.orderInfo.orderId
    }

    private fun createModifyOrderStatusKafkaDto(order: Order) : ModifyOrderStatusKafkaDto {
        return ModifyOrderStatusKafkaDto(
            modifyOrderStatusEventDto = ModifyOrderStatusEventDto(
                orderId = order.orderInfo.orderId,
                orderStatus = order.orderInfo.orderStatus),

            OrderEvent(
                eventUUID = UUID.randomUUID().toString(),
                orderId = order.orderInfo.orderId)
        )
    }

}