package com.uahannam.order.application.port.out

import com.uahannam.order.application.port.`in`.model.CreateOrderCommand

fun interface CreateOrderPort {
    fun createOrder(orderCommand: CreateOrderCommand) : Long
}