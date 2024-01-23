package com.uahannam.usecase

import com.uahannam.order.application.port.out.LoadOrderPort
import com.uahannam.order.application.service.LoadOrderService
import com.uahannam.order.domain.Order
import com.uahannam.order.domain.OrderItem
import com.uahannam.order.domain.OrderStatus
import com.uahannam.order.domain.Orderer
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class LoadOrderUseCaseTest {

    @MockK
    private lateinit var loadOrderPort: LoadOrderPort

    @InjectMockKs
    private lateinit var loadOrderUseCase: LoadOrderService

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
        every { loadOrderPort.loadOrderById(orderId) } returns expectedOrderData

        // when
        val actualData = loadOrderUseCase.loadOrderById(orderId)

        // then
        Assertions.assertThat(actualData).isEqualTo(expectedOrderData)
        verify { loadOrderUseCase.loadOrderById(orderId) }
    }
}

