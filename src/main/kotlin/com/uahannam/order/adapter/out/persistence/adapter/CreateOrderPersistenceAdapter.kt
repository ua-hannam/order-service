package com.uahannam.order.adapter.out.persistence.adapter

import com.uahannam.common.annotation.PersistenceAdapter
import com.uahannam.order.adapter.out.kafka.event.dto.SaveOrderEventDto
import com.uahannam.order.adapter.out.persistence.entity.OrderItemJpaEntity
import com.uahannam.order.adapter.out.persistence.entity.OrderJpaEntity
import com.uahannam.order.adapter.out.persistence.repository.OrderItemRepository
import com.uahannam.order.adapter.out.persistence.repository.OrderRepository
import com.uahannam.order.application.port.`in`.model.CreateOrderCommand
import com.uahannam.order.application.port.out.CreateOrderPort
import com.uahannam.order.domain.OrderStatus
import com.uahannam.order.utils.EventProducer

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
            totalPrice = orderCommand.totalPrice
        )

        orderRepository.save(order)

        val orderItems = orderCommand.orderItems.map {
                OrderItemJpaEntity(
                    itemId = it.itemId,
                    itemName = it.itemName,
                    itemQuantity = it.itemQuantity,
                    itemPrice = it.itemPrice,
                    itemTotalPrice = it.itemTotalPrice,
                    orderId = order.orderId!!
                )
        }

        orderItemRepository.saveAll(orderItems)

        EventProducer.produceEvent(
            SaveOrderEventDto(
                orderJpaEntity = order,
                orderItemJpaEntityList = orderItems
            )
        )

        return order.orderId!!
    }
}