package com.uahannam.order.application.port.out

import com.uahannam.order.application.port.`in`.model.CreateOrderCommand

interface CreateOrderPort {
    fun createOrder(orderCommand: CreateOrderCommand)
}