package com.uahannam.usecase

import com.appmattus.kotlinfixture.kotlinFixture
import com.uahannam.common.exception.CustomException
import com.uahannam.common.exception.ErrorCode.*
import com.uahannam.order.application.port.`in`.usecase.LoadOrderUseCase
import com.uahannam.order.application.port.out.persistence.ModifyOrderStatusPort
import com.uahannam.order.application.service.ModifyOrderStatusService
import com.uahannam.order.domain.Order
import com.uahannam.order.domain.OrderInfo
import com.uahannam.order.domain.OrderStatus
import com.uahannam.order.domain.OrderStatus.*
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class ModifyOrderStatusUseCaseTest : BehaviorSpec({

    val fixture = kotlinFixture()
    val modifyOrderStatusPort = mockk<ModifyOrderStatusPort>()
    val loadOrderUseCase = mockk<LoadOrderUseCase>()
    val modifyOrderStatusUseCase = ModifyOrderStatusService(loadOrderUseCase, modifyOrderStatusPort)

    afterContainer {
        clearAllMocks()
    }

    Given("주문 취소가 가능한 상황에서 취소 요청을 하면") {
        val orderId = 1L

        val orderInfo = fixture<OrderInfo> {
            property<OrderInfo, OrderStatus>("orderStatus") { RECEIPT }
            property<OrderInfo, Long>("orderId") { orderId }
        }

        val expectedOrder = fixture<Order> {
            property<Order, OrderInfo>("orderInfo") { orderInfo }
        }

        every { loadOrderUseCase.loadOrderById(orderId) } returns expectedOrder

        every { modifyOrderStatusPort.modifyOrderStatus(expectedOrder) } returns orderId

        When("주문 취소를 요청하면") {
            val result = modifyOrderStatusUseCase.modifyOrderStatus(orderId)

            Then("주문 취소가 성공해야 한다") {
                result shouldBe orderId

                verify(exactly = 1) { loadOrderUseCase.loadOrderById(orderId) }
                verify(exactly = 1) { modifyOrderStatusPort.modifyOrderStatus(expectedOrder) }
            }
        }
    }

    Given("주문 취소가 불가능한 상태에서 취소 요청을 하면") {
        val orderId = 1L

        val orderInfo = fixture<OrderInfo> {
            property<OrderInfo, OrderStatus>("orderStatus") { COOKING }
            property<OrderInfo, Long>("orderId") { orderId }
        }

        val expectedOrder = fixture<Order> {
            property<Order, OrderInfo>("orderInfo") { orderInfo }
        }

        every { loadOrderUseCase.loadOrderById(orderId) } returns expectedOrder

        every { modifyOrderStatusPort.modifyOrderStatus(expectedOrder) } returns orderId

        When("주문 취소를 요청하면") {
            val exception = shouldThrow<CustomException> {
                modifyOrderStatusUseCase.modifyOrderStatus(orderId)
            }

            Then("주문 취소가 성공해야 한다") {
                exception.errorCode shouldBe CANNOT_CANCEL_ORDER
                verify(exactly = 1) { loadOrderUseCase.loadOrderById(orderId) }
                verify(exactly = 0) { modifyOrderStatusPort.modifyOrderStatus(expectedOrder) }
            }
        }
    }

})