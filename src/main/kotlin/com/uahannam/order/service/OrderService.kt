package com.uahannam.order.service

import com.uahannam.order.domain.entity.Order
import com.uahannam.order.domain.request.OrderCreateRequestDto
import com.uahannam.order.domain.statusEnum.OrderStatus
import com.uahannam.order.repository.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Service
@Transactional(readOnly = true)
class OrderService(
        private val orderRepository: OrderRepository,
        private val orderItemService: OrderItemService
) {

    @Transactional
    fun saveOrder(orderRequest: OrderCreateRequestDto, memberId: Long): Order {

        // TODO: 결제 연동 필요

        var order = Order(
                memberId = memberId,
                storeId = orderRequest.storeId,
                totalPrice = orderRequest.totalPrice,
                orderStatus = OrderStatus.RECEIPT
        )

        order = orderRepository.save(order)
        orderItemService.saveOrderItems(orderId = order.orderId!!,
            orderItems = orderRequest.orderItems)

        return order
    }

    fun findAllOrders(): List<Order> = orderRepository.findAll()

    @Transactional
    fun modifyOrderStatus(orderStatus: OrderStatus, orderId: Long) {
        val order = orderRepository.findById(orderId)
            .orElseThrow()

        order.updateOrderStatus(orderStatus)
        orderRepository.save(order)
    }

}