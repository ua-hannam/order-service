package com.uahannam.order.application.service

import com.uahannam.order.application.port.`in`.usecase.LoadOrderUseCase
import com.uahannam.order.application.port.`in`.usecase.ModifyOrderStatusUseCase
import com.uahannam.order.application.port.out.persistence.ModifyOrderStatusPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ModifyOrderStatusService(
    private val loadOrderUseCase: LoadOrderUseCase,
    private val modifyOrderStatusPort: ModifyOrderStatusPort
) : ModifyOrderStatusUseCase {


    override fun modifyOrderStatus(orderId: Long): Long {
        val order = loadOrderUseCase.loadOrderById(orderId)
        order.requestCancel()
        return modifyOrderStatusPort.modifyOrderStatus(order)
    }
}