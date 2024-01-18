package com.uahannam.order.adapter.out.persistence.adapter

import com.uahannam.common.annotation.PersistenceAdapter
import com.uahannam.common.exception.OrderNotFoundException
import com.uahannam.order.adapter.out.persistence.repository.OrderItemRepository
import com.uahannam.order.adapter.out.persistence.repository.OrderRepository
import com.uahannam.order.application.port.out.LoadOrderPort
import com.uahannam.order.domain.Order
import com.uahannam.order.domain.OrderItem
import com.uahannam.order.domain.Orderer

@PersistenceAdapter
class LoadOrderPersistenceAdapter(
    private val orderRepository: OrderRepository,
    private val orderItemRepository: OrderItemRepository
) : LoadOrderPort {
    override fun loadOrderById(orderId: Long): Order {
        val orderJpaEntity = orderRepository.findById(orderId)
            .orElseThrow(::OrderNotFoundException)

        val orderItemList = orderItemRepository.findByOrderId(orderId)
            .map {
                OrderItem(
                    itemName = it.itemName,
                    itemPrice = it.itemPrice,
                    itemQuantity = it.itemQuantity
                )
            }

        val orderer = Orderer(
            name = "Dev Seo Rex",
            address = orderJpaEntity.address,
            phoneNumber = "010-1111-1111"
        )

        return Order(orderer, orderItemList, orderJpaEntity.orderStatus)
    }
}