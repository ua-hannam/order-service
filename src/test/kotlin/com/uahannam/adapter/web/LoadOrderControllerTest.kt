package com.uahannam.adapter.web

import com.appmattus.kotlinfixture.kotlinFixture
import com.fasterxml.jackson.databind.ObjectMapper
import com.uahannam.common.dto.BaseResponse
import com.uahannam.common.exception.CustomException
import com.uahannam.common.exception.ErrorCode.*
import com.uahannam.common.exception.GlobalCatcher
import com.uahannam.order.adapter.`in`.web.LoadOrderController
import com.uahannam.order.application.port.`in`.usecase.LoadOrderUseCase
import com.uahannam.order.domain.Order
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.*
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest(classes = [ObjectMapper::class])
internal class LoadOrderControllerTest(
    private val objectMapper: ObjectMapper
) : DescribeSpec() {

    private val fixture = kotlinFixture()
    private lateinit var loadOrderUseCase: LoadOrderUseCase
    private lateinit var mockMvc: MockMvc

    init {

        this.describe("GET /api/orders/{orderId} -> 정상 케이스") {
            val expectedOrder = fixture<Order>()

            loadOrderUseCase = mockk {
                every { loadOrderById(any()) } returns expectedOrder
            }

            mockMvc = MockMvcBuilders.standaloneSetup(
                LoadOrderController(loadOrderUseCase)
            ).build()

            context("주문 조회를 위해 주문 PK가 전달되면") {
                val orderId = 1L

                it("주문이 조회 되어야 한다" ) {
                    val responseOrder = mockMvc.perform(get("/api/orders/$orderId")
                            .contentType(APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk)
                        .andReturn()
                        .response.contentAsString

                    val expectedOrderResponse = objectMapper.writeValueAsString(BaseResponse(expectedOrder))

                    responseOrder shouldBe expectedOrderResponse
                    verify(exactly = 1) { loadOrderUseCase.loadOrderById(any()) }

                    }
                }
            }

        this.describe("GET /api/orders/{orderId} -> 비정상 케이스") {

            loadOrderUseCase = mockk {
                every { loadOrderById(any()) } throws CustomException(ORDER_NOT_FOUND)
            }

            mockMvc = MockMvcBuilders.standaloneSetup(
                LoadOrderController(loadOrderUseCase)
            ).setControllerAdvice(GlobalCatcher())
            .build()

            context("주문 조회를 위해 존재하지 않는 주문 PK가 전달되면") {
                val orderId = 1L

                it("예외가 발생해야 한다" ) {
                    mockMvc.perform(get("/api/orders/$orderId")
                            .contentType(APPLICATION_JSON))
                            .andDo(print())
                            .andExpect(status().isBadRequest)
                            .andExpect(jsonPath("$.serial").value("ERROR_01"))

                    verify(exactly = 1) { loadOrderUseCase.loadOrderById(any()) }

                }
            }
        }

    }
}