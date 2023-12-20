package com.uahannam.order.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity(name = "ORDER_EVENT")
class OrderEvent(
        @Id
        @Column(name = "ORDER_EVENT_ID")
        val orderEventId: Long? = null,

        @Column(name = "EVENT_UUID")
        val eventUUID: String,

        @Column(name = "ORDER_ID")
        val orderId: Long,

): DateBaseEntity() {
}