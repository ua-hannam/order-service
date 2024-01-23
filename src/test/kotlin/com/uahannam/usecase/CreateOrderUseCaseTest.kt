package com.uahannam.usecase

import com.uahannam.order.application.port.`in`.model.CreateOrderCommand
import com.uahannam.order.application.port.`in`.model.CreateOrderItemCommand
import com.uahannam.order.application.port.out.CreateOrderPort
import com.uahannam.order.application.service.CreateOrderService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class CreateOrderUseCaseTest {


    @MockK
    private lateinit var createOrderPort: CreateOrderPort

    @InjectMockKs
    private lateinit var createOrderUseCase: CreateOrderService

    @Test
    fun `주문 생성 정상 케이스 테스트`() {
        // given
        val itemList = listOf(
            CreateOrderItemCommand(1L, "서브웨이", 9500, 2, 19000),
            CreateOrderItemCommand(3L, "버거킹", 13000, 2, 26000)
        )

        val orderCommand = CreateOrderCommand(
            storeId = 7L,
            totalPrice = 45000,
            address = "서울시 마포구",
            orderItems = itemList
        )

        every { createOrderPort.createOrder(orderCommand) } returns Unit

        // when
        val actualData = createOrderUseCase.createOrder(orderCommand)

        // then
        Assertions.assertThat(actualData).isEqualTo(Unit)
        verify { createOrderUseCase.createOrder(orderCommand) }
    }
}