package com.uahannam.usecase

import com.appmattus.kotlinfixture.kotlinFixture
import com.uahannam.order.application.port.out.LoadOrderPort
import com.uahannam.order.application.service.LoadOrderService
import com.uahannam.order.domain.Order
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify



class LoadOrderUseCaseTest : BehaviorSpec({

    val fixture = kotlinFixture()
    val loadOrderPort = mockk<LoadOrderPort>()
    val loadOrderUseCase = LoadOrderService(loadOrderPort)

    Given("주문을 조회하려고 하는 경우") {
        val orderId = 1L
        val expectedOrderData = fixture<Order>()

        every { loadOrderPort.loadOrderById(orderId) } returns expectedOrderData

        When("특정 주문 ID로 주문을 검색하면") {
            val actualData = loadOrderUseCase.loadOrderById(orderId)

            Then("일치하는 주문건이 조회되어야 한다") {
                actualData shouldBe expectedOrderData
                verify(exactly = 1) { loadOrderPort.loadOrderById(orderId) }
            }
        }
    }
})

