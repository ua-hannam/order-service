package com.uahannam.domain

import com.uahannam.common.exception.CustomException
import com.uahannam.order.domain.Order
import com.uahannam.order.domain.OrderItem
import com.uahannam.order.domain.OrderStatus
import com.uahannam.order.domain.Orderer
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class OrderDomainTest {

    @Test
    fun `주문 취소 요청 정상 케이스 테스트`() {
        // given
        val order = Order(
            orderer = Orderer("Dev Seo Rex", "010-1111-1111", "서울시 마포구"),
            orderItem = listOf(
                OrderItem("로세라티 치킨", 7000, 2),
                OrderItem("서브웨이 클럽", 6500, 2)
            ),
            orderStatus = OrderStatus.RECEIPT
        )

        // when
        order.requestCancel()

        // then
        Assertions.assertThat(order.orderStatus).isEqualTo(OrderStatus.USER_CANCEL_REQUEST)
    }

    @Test
    fun `주문 취소 비정상 케이스 테스트 - 조리 중 취소 불가`() {
        // given
        val order = Order(
            orderer = Orderer("Dev Seo Rex", "010-1111-1111", "서울시 마포구"),
            orderItem = listOf(
                OrderItem("로세라티 치킨", 7000, 2),
                OrderItem("서브웨이 클럽", 6500, 2)
            ),
            orderStatus = OrderStatus.COOKING
        )

        // when
        val exception = Assertions.assertThatThrownBy { order.requestCancel() }

        // then
        Assertions.assertThat(order.orderStatus).isEqualTo(OrderStatus.COOKING)
        exception.isInstanceOf(CustomException::class.java)
    }
}