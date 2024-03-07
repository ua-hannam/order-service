package com.uahannam.order.application.port.`in`.usecase

import com.uahannam.order.application.port.`in`.model.CreateOrderCommand

interface CreateOrderUseCase {

    fun createOrder(orderCommand: CreateOrderCommand) : Long
}