package com.uahannam.order.application.service

import com.uahannam.order.application.port.`in`.usecase.LoadOrderUseCase
import com.uahannam.order.application.port.out.LoadOrderPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class LoadOrderService(
    private val loadOrderPort: LoadOrderPort
) : LoadOrderUseCase {
    override fun loadOrderById(orderId: Long) =
        loadOrderPort.loadOrderById(orderId)
}