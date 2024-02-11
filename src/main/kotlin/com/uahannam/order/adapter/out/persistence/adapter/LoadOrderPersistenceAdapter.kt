package com.uahannam.order.adapter.out.persistence.adapter

import com.uahannam.common.annotation.PersistenceAdapter
import com.uahannam.common.exception.CustomException
import com.uahannam.common.exception.ErrorCode.*
import com.uahannam.order.adapter.out.persistence.mapper.OrderMapper
import com.uahannam.order.adapter.out.persistence.repository.OrderItemRepository
import com.uahannam.order.adapter.out.persistence.repository.OrderRepository
import com.uahannam.order.application.port.out.persistence.LoadOrderPort
import com.uahannam.order.domain.Order

@PersistenceAdapter
class LoadOrderPersistenceAdapter(
    private val orderRepository: OrderRepository,
    private val orderItemRepository: OrderItemRepository
) : LoadOrderPort {
    override fun loadOrderById(orderId: Long): Order {
        val orderJpaEntity = orderRepository.findById(orderId)
            .orElseThrow { throw CustomException(ORDER_NOT_FOUND) }

        val orderItemList = orderItemRepository.findByOrderId(orderId)

        return OrderMapper.mapToDomainEntity(orderJpaEntity, orderItemList)
    }
}