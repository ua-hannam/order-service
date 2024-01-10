package com.uahannam.order.adapter.out.persistence.entity

import jakarta.persistence.*

@Entity(name = "ORDERS")
class OrderJpaEntity(
    @Id
    @Column(name = "ORDER_ID")
    val orderId: Long? = null,

    @Column(name = "MEMBER_ID")
    val memberId: Long,

    @Column(name = "STORE_ID")
    val storeId: Long,

    @Column(name = "TOTAL_PRICE")
    val totalPrice: Int,

    @Column(name = "ORDER_STATUS")
    @Enumerated(EnumType.STRING)
    var orderStatus: OrderStatus,

    ) : DateBaseEntity() {
        fun updateOrderStatus(orderStatus: OrderStatus) {
            this.orderStatus = orderStatus
        }
}