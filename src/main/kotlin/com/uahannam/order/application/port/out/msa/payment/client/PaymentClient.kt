package com.uahannam.order.application.port.out.msa.payment.client

import com.uahannam.order.adapter.out.msa.payment.dto.PaymentRequest
import com.uahannam.order.adapter.out.msa.payment.dto.PaymentResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping

@FeignClient("payment-service")
interface PaymentClient {

    @PostMapping("/charges")
    fun proceedPayment(paymentRequest: PaymentRequest) : PaymentResponse
}