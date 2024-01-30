package com.uahannam.order.application.port.out

import com.uahannam.order.domain.Order

fun interface LoadOrderPort {

    fun loadOrderById(orderId: Long): Order
}