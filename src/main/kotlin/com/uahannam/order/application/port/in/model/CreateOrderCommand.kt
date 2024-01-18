package com.uahannam.order.application.port.`in`.model

class CreateOrderCommand(
    val storeId: Long,
    val totalPrice: Int,
    val address: String,
    val orderItems: List<CreateOrderItemCommand>
)