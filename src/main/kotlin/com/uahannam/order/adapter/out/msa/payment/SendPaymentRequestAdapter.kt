package com.uahannam.order.adapter.out.msa.payment

import com.uahannam.common.annotation.MsaServiceAdapter
import com.uahannam.order.adapter.out.msa.payment.dto.PaymentRequest
import com.uahannam.order.application.port.out.msa.payment.SendPaymentRequestPort
import com.uahannam.order.application.port.out.msa.payment.client.PaymentClient

@MsaServiceAdapter
class SendPaymentRequestAdapter(
    private val paymentClient: PaymentClient
) : SendPaymentRequestPort {

    override fun sendPaymentRequest(paymentRequest: PaymentRequest) =
        paymentClient.proceedPayment(paymentRequest)
}