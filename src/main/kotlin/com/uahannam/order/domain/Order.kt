package com.uahannam.order.domain

import com.uahannam.common.exception.CustomException
import com.uahannam.common.exception.ErrorCode.*

class Order(
    val orderer: Orderer,
    val orderItem: List<OrderItem>,
    var orderStatus: OrderStatus,
) {

    fun requestCancel() {
        // 배달 중이거나 조리 중이 아니라면 취소 요청을 접수할 수 있다.
        if (orderStatus == OrderStatus.SHIPPING || orderStatus == OrderStatus.COOKING) {
            throw CustomException(CANNOT_CANCEL_ORDER)
        }
        orderStatus = OrderStatus.USER_CANCEL_REQUEST
    }
}