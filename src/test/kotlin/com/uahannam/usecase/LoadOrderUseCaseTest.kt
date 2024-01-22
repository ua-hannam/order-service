package com.uahannam.usecase

import com.uahannam.order.application.port.out.LoadOrderPort
import com.uahannam.order.application.service.LoadOrderService
import com.uahannam.order.domain.Order
import com.uahannam.order.domain.OrderItem
import com.uahannam.order.domain.OrderStatus
import com.uahannam.order.domain.Orderer
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class LoadOrderUseCaseTest {

    private val loadOrderPort = mockk<LoadOrderPort>()
    private val loadOrderService = LoadOrderService(loadOrderPort)

    @Test
    fun `주문 아이디로 주문 찾기 테스트`() {
        // given
        val orderId = 1L
        val expectedOrderData = Order(
            orderer = Orderer("Dev Seo Rex", "010-1111-1111", "서울시 마포구"),
            orderItem = listOf(
                OrderItem("로세라티 치킨", 7000, 2),
                OrderItem("서브웨이 클럽", 6500, 2)
            ),
            orderStatus = OrderStatus.RECEIPT
        )
        every { loadOrderService.loadOrderById(orderId) } returns expectedOrderData

        // when
        val actualData = loadOrderService.loadOrderById(orderId)

        // then
        Assertions.assertThat(actualData).isEqualTo(expectedOrderData)
        verify { loadOrderService.loadOrderById(orderId) }
    }
}

