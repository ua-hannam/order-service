package com.uahannam.order.domain.statusEnum

enum class OrderStatus(
        val status: String,
        val description: String
) {

    RECEIPT("RECEIPT", "주문 접수"),
    USER_CANCEL_REQUEST("USER_CANCEL_REQUEST", "사용자 취소 요청"),
    USER_CANCEL("USER_CANCEL", "사용자 취소"),
    STORE_CANCEL("STORE_CANCEL", "가게 사정 취소"),
    APPROVAL("APPROVAL", "주문 승인")
}