package com.uahannam.order.domain.request

class OrderCreateRequestDto(
        var storeId: Long,
        var totalPrice: Int,
        var orderItems: List<OrderItemDto>
) {
}

class OrderItemDto(
        var itemId: Long,
        var itemName: String,
        var itemPrice: Int,
        var itemQuantity: Int,
        var itemTotalPrice: Int
) {

}