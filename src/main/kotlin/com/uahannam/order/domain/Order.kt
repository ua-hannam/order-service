package com.uahannam.order.domain

class Order(
    val orderer: Orderer,
    val orderItem: List<OrderItem>,
    val orderStatus: OrderStatus,
) {

}