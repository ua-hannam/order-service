package com.uahannam.order.application.port.out

import com.uahannam.order.domain.Order

interface ModifyOrderPort {

    fun modifyOrderStatus(order: Order) : Long
}