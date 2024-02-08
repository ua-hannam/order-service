package com.uahannam.order.application.port.`in`.usecase

import com.uahannam.common.annotation.UseCase

@UseCase
fun interface ModifyOrderStatusUseCase {

    fun modifyOrderStatus(orderId: Long) : Long
}