package com.uahannam.order.domain.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("ORDER_ITEM")
class OrderItem(
        @Id
        @Column("ORDER_ITEM_ID")
        var orderItemId: Long = 0L,

        @Column("ORDER_ID")
        var orderId: Long,

        @Column("ITEM_ID")
        var itemId: Long,

        @Column("ITEM_PRICE")
        var itemPrice: Int,

        @Column("ITEM_NAME")
        var itemName: String,

        @Column("ITEM_QUANTITY")
        var itemQuantity: Int,

        @Column("ITEM_TOTAL_PRICE")
        var itemTotalPrice: Int,

        @CreatedDate
        @Column("REG_DATE")
        var regDate: LocalDateTime? = null,

        @LastModifiedDate
        @Column("MOD_DATE")
        var modDate: LocalDateTime? = null
) {
}