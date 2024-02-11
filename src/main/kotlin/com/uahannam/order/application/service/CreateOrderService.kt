package com.uahannam.order.application.service

import com.uahannam.common.annotation.UseCase
import com.uahannam.order.application.port.`in`.model.CreateOrderCommand
import com.uahannam.order.application.port.`in`.usecase.CreateOrderUseCase
import com.uahannam.order.application.port.out.persistence.CreateOrderPort
import org.springframework.transaction.annotation.Transactional

@UseCase
@Transactional
class CreateOrderService(
    private val createOrderPort: CreateOrderPort
) : CreateOrderUseCase {
    override fun createOrder(orderCommand: CreateOrderCommand) : Long {
        // 결제 연동 필요 -> 채훈님 빨리!

        // 주문 데이터 유효성 검증
        orderCommand.validate()
        return createOrderPort.createOrder(orderCommand)
    }
}