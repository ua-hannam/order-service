package com.uahannam.order.application.port.`in`.model

class CreateOrderItemCommand(
    val itemId: Long,
    val itemName: String,
    val itemPrice: Int,
    val itemQuantity: Int,
    val itemTotalPrice: Int
)