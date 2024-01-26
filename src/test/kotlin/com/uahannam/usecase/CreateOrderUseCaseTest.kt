package com.uahannam.usecase

import com.appmattus.kotlinfixture.kotlinFixture
import com.uahannam.order.application.port.`in`.model.CreateOrderCommand
import com.uahannam.order.application.port.out.CreateOrderPort
import com.uahannam.order.application.service.CreateOrderService
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify


class CreateOrderUseCaseTest : BehaviorSpec({

    val createOrderPort = mockk<CreateOrderPort>()
    val createOrderUseCase = CreateOrderService(createOrderPort)
    val fixture = kotlinFixture()

    Given("유저가 상품을 주문 상품을 담고, 결제가 완료된 상태에서") {
        val orderCommand = fixture<CreateOrderCommand>()
        every { createOrderPort.createOrder(orderCommand) } returns 1L

        When("배달 주문을 요청하면") {
            val actualData = createOrderUseCase.createOrder(orderCommand)
            Then("정상적으로 주문이 생성(접수)되어야 한다") {
                actualData shouldBe Unit
                verify(exactly = 1) { createOrderPort.createOrder(orderCommand) }
            }
        }
    }
})