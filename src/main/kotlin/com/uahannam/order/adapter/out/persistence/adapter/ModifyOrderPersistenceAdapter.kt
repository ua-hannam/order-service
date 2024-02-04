package com.uahannam.order.adapter.out.persistence.adapter

import com.uahannam.common.annotation.PersistenceAdapter
import com.uahannam.common.util.EventProducer
import com.uahannam.order.adapter.out.kafka.event.dto.ModifyOrderStatusEventDto
import com.uahannam.order.adapter.out.kafka.produce.dto.ModifyOrderStatusKafkaDto
import com.uahannam.order.adapter.out.kafka.produce.dto.OrderEvent
import com.uahannam.order.adapter.out.persistence.repository.OrderRepository
import com.uahannam.order.application.port.out.ModifyOrderPort
import com.uahannam.order.domain.Order
import java.util.UUID

@PersistenceAdapter
class ModifyOrderPersistenceAdapter(
    private val orderRepository: OrderRepository
) : ModifyOrderPort {


    override fun modifyOrderStatus(order: Order): Long {
        orderRepository.modifyOrderStatus(order.orderInfo.orderStatus, order.orderInfo.orderId)

        EventProducer.produceEvent(
            ModifyOrderStatusKafkaDto(
                modifyOrderStatusEventDto = ModifyOrderStatusEventDto(
                    orderId = order.orderInfo.orderId,
                    orderStatus = order.orderInfo.orderStatus),

            OrderEvent(
                eventUUID = UUID.randomUUID().toString(),
                orderId = order.orderInfo.orderId)
            )
        )

        return order.orderInfo.orderId
    }

}