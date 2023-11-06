package com.uahannam.order.domain.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("ORDER_EVENT")
class OrderEvent(
        @Id
        @Column("ORDER_EVENT_ID")
        var orderEventId: Long = 0L,

        @Column("EVENT_UUID")
        var eventUUID: String,

        @Column("ORDER_ID")
        var orderId: Long,

        @CreatedDate
        @Column("REG_DATE")
        var regDate: LocalDateTime,

        @LastModifiedDate
        @Column("MOD_DATE")
        var modDate: LocalDateTime
) {
}