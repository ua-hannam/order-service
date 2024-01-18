package com.uahannam.order.application.service

import com.uahannam.order.adapter.out.persistence.adapter.LoadOrderPersistenceAdapter
import com.uahannam.order.application.port.`in`.usecase.LoadOrderUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class LoadOrderService(
    private val loadOrderPersistenceAdapter: LoadOrderPersistenceAdapter
) : LoadOrderUseCase {
    override fun loadOrderById(orderId: Long) =
        loadOrderPersistenceAdapter.loadOrderById(orderId)
}