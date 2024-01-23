package com.uahannam.order.adapter.out.persistence.entity

import jakarta.persistence.*


@Entity(name = "ORDER_ITEM")
data class OrderItemJpaEntity(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ORDER_ITEM_ID")
        val orderItemId: Long? = null,

        @Column(name = "ORDER_ID")
        val orderId: Long,

        @Column(name = "ITEM_ID")
        val itemId: Long,

        @Column(name = "ITEM_PRICE")
        val itemPrice: Int,

        @Column(name = "ITEM_NAME")
        val itemName: String,

        @Column(name = "ITEM_QUANTITY")
        val itemQuantity: Int,

        @Column(name = "ITEM_TOTAL_PRICE")
        val itemTotalPrice: Int
) : DateBaseEntity()
