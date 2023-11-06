package com.uahannam.order.service

import com.uahannam.order.domain.entity.Order
import com.uahannam.order.domain.request.OrderRequestDto
import com.uahannam.order.repository.OrderRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class OrderService(
        private val orderRepository: OrderRepository
) {

    fun saveOrder(orderRequest: OrderRequestDto, memberId: Long): Mono<Order> {

        // TODO: 결제 연동 필요

        val order = Order(
                memberId = memberId,
                storeId = orderRequest.storeId,
                totalPrice = orderRequest.totalPrice,
                orderStatus = "receipt"
        )

        return orderRepository.save(order)
    }

    fun findAllOrders() = orderRepository.findAll()


}