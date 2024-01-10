package com.uahannam.order.adapter.out.persistence.entity

enum class OrderStatus(
    val description: String
) {

    RECEIPT("주문 접수"),
    USER_CANCEL_REQUEST("사용자 취소 요청"),
    USER_CANCEL("사용자 취소"),
    STORE_CANCEL("가게 사정 취소"),
    APPROVAL("주문 승인")
}