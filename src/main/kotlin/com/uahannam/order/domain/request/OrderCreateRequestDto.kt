package com.uahannam.order.domain.request

class OrderCreateRequestDto(
        val storeId: Long,
        val totalPrice: Int,
        val orderItems: List<OrderItemDto>
) {
}

class OrderItemDto(
        val itemId: Long,
        val itemName: String,
        val itemPrice: Int,
        val itemQuantity: Int,
        val itemTotalPrice: Int
) {

}