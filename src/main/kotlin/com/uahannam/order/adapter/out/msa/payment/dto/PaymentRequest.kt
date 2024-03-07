package com.uahannam.order.adapter.out.msa.payment.dto

data class PaymentRequest(
    val userId: Long,
    val amount: Int
)