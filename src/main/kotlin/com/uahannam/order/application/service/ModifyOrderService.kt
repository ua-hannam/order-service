package com.uahannam.order.application.service

import com.uahannam.order.application.port.`in`.usecase.LoadOrderUseCase
import com.uahannam.order.application.port.`in`.usecase.ModifyOrderUseCase
import com.uahannam.order.application.port.out.ModifyOrderPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ModifyOrderService(
    private val loadOrderUseCase: LoadOrderUseCase,
    private val modifyOrderPort: ModifyOrderPort
) : ModifyOrderUseCase {


    override fun modifyOrderStatus(orderId: Long): Long {
        val order = loadOrderUseCase.loadOrderById(orderId)
        order.requestCancel()
        return modifyOrderPort.modifyOrderStatus(order)
    }
}