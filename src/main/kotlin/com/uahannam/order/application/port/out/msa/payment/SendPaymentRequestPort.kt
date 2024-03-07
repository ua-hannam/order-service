package com.uahannam.order.application.port.out.msa.payment

import com.uahannam.order.adapter.out.msa.payment.dto.PaymentRequest
import com.uahannam.order.adapter.out.msa.payment.dto.PaymentResponse

interface SendPaymentRequestPort {

    fun sendPaymentRequest(paymentRequest: PaymentRequest) : PaymentResponse
}