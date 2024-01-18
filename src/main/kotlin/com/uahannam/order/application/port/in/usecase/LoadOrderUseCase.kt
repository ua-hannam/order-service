package com.uahannam.order.application.port.`in`.usecase

import com.uahannam.common.annotation.UseCase
import com.uahannam.order.domain.Order

@UseCase
interface LoadOrderUseCase {

    fun loadOrderById(orderId: Long): Order
}