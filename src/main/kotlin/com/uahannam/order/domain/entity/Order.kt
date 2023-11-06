package com.uahannam.order.domain.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("ORDERS")
class Order(
        @Id
        @Column("ORDER_ID")
        var orderId: Long = 0L,

        @Column("MEMBER_ID")
        var memberId: Long,

        @Column("STORE_ID")
        var storeId: Long,

        @Column("TOTAL_PRICE")
        var totalPrice: Int,

        @Column("ORDER_STATUS")
        var orderStatus: String,

        @CreatedDate
        @Column("REG_DATE")
        var regDate: LocalDateTime? = null,

        @LastModifiedDate
        @Column("MOD_DATE")
        var modDate: LocalDateTime? = null
) {
}