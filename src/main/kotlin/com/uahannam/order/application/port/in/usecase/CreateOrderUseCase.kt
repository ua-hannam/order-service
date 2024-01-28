package com.uahannam.order.application.port.`in`.usecase

import com.uahannam.common.annotation.UseCase
import com.uahannam.order.application.port.`in`.model.CreateOrderCommand

@UseCase
interface CreateOrderUseCase {

    fun createOrder(orderCommand: CreateOrderCommand) : Long
}