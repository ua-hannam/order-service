package com.uahannam.order.service

import com.uahannam.order.domain.entity.OrderItem
import com.uahannam.order.domain.request.OrderItemDto
import com.uahannam.order.repository.OrderItemRepository
import org.springframework.stereotype.Service

@Service
class OrderItemService(
        private val orderItemRepository: OrderItemRepository
) {

    fun saveOrderItems(orderId: Long, orderItems: List<OrderItemDto>) {
        val orderItemList = orderItems
                .stream()
                .map {
                    OrderItem(
                            itemId = it.itemId,
                            orderId = orderId,
                            itemName = it.itemName,
                            itemTotalPrice = it.itemTotalPrice,
                            itemPrice = it.itemPrice,
                            itemQuantity = it.itemQuantity
                    )
                }.toList()

        orderItemRepository.saveAll(orderItemList)
                .subscribe()
    }

    fun findAllOrderItems() = orderItemRepository.findAll()

    fun findAllOrderItemsByOrderId(orderId: Long) = orderItemRepository.findByOrderId(orderId)
}