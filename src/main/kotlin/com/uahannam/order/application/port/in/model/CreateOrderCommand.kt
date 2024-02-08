package com.uahannam.order.application.port.`in`.model

data class CreateOrderCommand(
    val storeId: Long,
    val totalPrice: Int,
    val address: String,
    val orderItems: List<CreateOrderItemCommand>
)