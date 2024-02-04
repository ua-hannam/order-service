package com.uahannam.order.domain

import com.uahannam.common.exception.CustomException
import com.uahannam.common.exception.ErrorCode.*

data class Order(
    val orderInfo: OrderInfo,
    val orderer: Orderer,
    val orderItem: List<OrderItem>,
) {

    fun requestCancel() {
        // 배달 중이거나 조리 중이 아니라면 취소 요청을 접수할 수 있다.
        if (orderInfo.orderStatus == OrderStatus.SHIPPING || orderInfo.orderStatus == OrderStatus.COOKING) {
            throw CustomException(CANNOT_CANCEL_ORDER)
        }
        orderInfo.orderStatus = OrderStatus.USER_CANCEL_REQUEST
    }
}