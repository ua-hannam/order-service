package com.uahannam.order.domain

data class OrderInfo(
    val orderId: Long,
    val storedId: Long,
    val totalPrice: Int,
    val delStatus: Boolean,
    var orderStatus: OrderStatus,
)