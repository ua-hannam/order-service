package com.uahannam.order.adapter.out.persistence.entity

import com.uahannam.order.domain.OrderStatus
import jakarta.persistence.*

@Entity(name = "ORDERS")
data class OrderJpaEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    val orderId: Long? = null,

    @Column(name = "MEMBER_ID")
    val memberId: Long,

    @Column(name = "ADDRESS")
    val address: String,

    @Column(name = "STORE_ID")
    val storeId: Long,

    @Column(name = "TOTAL_PRICE")
    val totalPrice: Int,

    @Column(name = "ORDER_STATUS")
    @Enumerated(EnumType.STRING)
    var orderStatus: OrderStatus,

    @Column(name = "DEL_STATUS")
    var delStatus: Boolean = false

    ) : DateBaseEntity() {
    fun updateOrderStatus(orderStatus: OrderStatus) {
        this.orderStatus = orderStatus
    }

}