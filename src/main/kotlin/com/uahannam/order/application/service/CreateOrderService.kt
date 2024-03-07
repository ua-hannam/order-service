package com.uahannam.order.application.service

import com.uahannam.common.annotation.UseCase
import com.uahannam.common.exception.CustomException
import com.uahannam.common.exception.ErrorCode.*
import com.uahannam.order.adapter.out.msa.payment.dto.PaymentRequest
import com.uahannam.order.adapter.out.msa.payment.dto.PaymentStatus
import com.uahannam.order.application.port.`in`.model.CreateOrderCommand
import com.uahannam.order.application.port.`in`.usecase.CreateOrderUseCase
import com.uahannam.order.application.port.out.msa.payment.SendPaymentRequestPort
import com.uahannam.order.application.port.out.persistence.CreateOrderPort
import org.springframework.transaction.annotation.Transactional

@UseCase
@Transactional
class CreateOrderService(
    private val createOrderPort: CreateOrderPort,
    private val paymentRequestPort: SendPaymentRequestPort
) : CreateOrderUseCase {
    override fun createOrder(orderCommand: CreateOrderCommand) : Long {
        // 결제 연동 중 #1
        val paymentResult = paymentRequestPort.sendPaymentRequest(
            PaymentRequest(userId = 1L, amount = orderCommand.totalPrice)
        ).paymentStatus

        if (paymentResult != PaymentStatus.SUCCESS) throw CustomException(PAYMENT_PROCEEDING_ERROR)

        // 주문 데이터 유효성 검증
        orderCommand.validate()
        return createOrderPort.createOrder(orderCommand)
    }
}