package com.uahannam.order.adapter.out.msa.payment.dto

data class PaymentResponse(
    val paymentStatus: PaymentStatus
)

enum class PaymentStatus {
    PENDING,
    SUCCESS,
    FAILED,  // 결제 실패
    CANCELED,
    DECLINED // Point 가 부족한 경우
}