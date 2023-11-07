package com.uahannam.order.domain.request

import com.uahannam.order.domain.statusEnum.OrderStatus

class OrderModifyRequestDto(
        val orderStatus: OrderStatus
) {
}