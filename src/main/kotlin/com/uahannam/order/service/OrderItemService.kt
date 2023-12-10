package com.uahannam.order.service

import com.uahannam.order.domain.entity.OrderItem
import com.uahannam.order.domain.request.OrderItemDto
import com.uahannam.order.repository.OrderItemRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class OrderItemService(
        private val orderItemRepository: OrderItemRepository
) {

    @Transactional
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
    }

    fun findAllOrderItems(): List<OrderItem> = orderItemRepository.findAll()

    fun findAllOrderItemsByOrderId(orderId: Long): List<OrderItem> = orderItemRepository.findByOrderId(orderId)
}