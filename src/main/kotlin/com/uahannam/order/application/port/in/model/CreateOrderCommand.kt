package com.uahannam.order.application.port.`in`.model

import com.uahannam.common.util.*

data class CreateOrderCommand(
    val storeId: Long,
    val totalPrice: Int,
    val address: String,
    val orderItems: List<CreateOrderItemCommand>
) {

    fun validate() {
        requireLongValue(storeId)
        requireIntValue(totalPrice)
        requireStringValue(address)
        requireCollection(orderItems)
    }
}