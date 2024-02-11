package com.uahannam.order.adapter.out.persistence.adapter

import com.uahannam.common.annotation.PersistenceAdapter
import com.uahannam.common.util.EventProducer
import com.uahannam.order.adapter.out.messaging.produce.dto.OrderEvent
import com.uahannam.order.adapter.out.messaging.produce.dto.SaveOrderKafkaDto
import com.uahannam.order.adapter.out.persistence.entity.OrderItemJpaEntity
import com.uahannam.order.adapter.out.persistence.entity.OrderJpaEntity
import com.uahannam.order.adapter.out.persistence.mapper.OrderMapper
import com.uahannam.order.adapter.out.persistence.repository.OrderItemRepository
import com.uahannam.order.adapter.out.persistence.repository.OrderRepository
import com.uahannam.order.application.port.`in`.model.CreateOrderCommand
import com.uahannam.order.application.port.out.persistence.CreateOrderPort
import com.uahannam.order.domain.OrderStatus
import java.util.*

@PersistenceAdapter
class CreateOrderPersistenceAdapter(
    private val orderRepository: OrderRepository,
    private val orderItemRepository: OrderItemRepository
) : CreateOrderPort {
    override fun createOrder(orderCommand: CreateOrderCommand) : Long {
        val order = OrderJpaEntity(
            memberId = 1L,
            storeId = orderCommand.storeId,
            address = orderCommand.address,
            orderStatus = OrderStatus.RECEIPT,
            totalPrice = orderCommand.totalPrice,
        )

        orderRepository.save(order)

        val orderItems = orderCommand.orderItems.map {
                OrderItemJpaEntity(
                    itemId = it.itemId,
                    itemName = it.itemName,
                    itemQuantity = it.itemQuantity,
                    itemPrice = it.itemPrice,
                    itemTotalPrice = it.itemTotalPrice,
                    orderId = order.orderId!!,
                )
        }

        orderItemRepository.saveAll(orderItems)

        // 이벤트 발행 -> Read-Model 서비스 연동
        EventProducer.produceEvent(
            createSaveOrderKafkaDto(
                orderJpaEntity = order,
                orderItemJpaEntityList = orderItems
            )
        )

        return order.orderId!!
    }

    private fun createSaveOrderKafkaDto(orderJpaEntity: OrderJpaEntity,
                                        orderItemJpaEntityList: List<OrderItemJpaEntity>) : SaveOrderKafkaDto {
        val order = OrderMapper.mapToOrderEventDto(orderJpaEntity)
        val orderItemList = OrderMapper.mapToOrderItemEventDto(orderItemJpaEntityList)

        val orderEvent = OrderEvent(
            eventUUID = UUID.randomUUID().toString(),
            orderId = order.orderId
        )

        return SaveOrderKafkaDto(order, orderItemList, orderEvent)
    }
}