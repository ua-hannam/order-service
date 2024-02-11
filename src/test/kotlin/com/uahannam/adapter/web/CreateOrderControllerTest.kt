package com.uahannam.adapter.web

import com.appmattus.kotlinfixture.kotlinFixture
import com.fasterxml.jackson.databind.ObjectMapper
import com.uahannam.common.dto.base.BaseResponse
import com.uahannam.order.adapter.`in`.web.CreateOrderController
import com.uahannam.order.application.port.`in`.model.CreateOrderCommand
import com.uahannam.order.application.port.`in`.usecase.CreateOrderUseCase
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.*
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@SpringBootTest(classes = [ObjectMapper::class])
internal class CreateOrderControllerTest(
    private val objectMapper: ObjectMapper
) : DescribeSpec() {

    private val fixture = kotlinFixture()
    private lateinit var createOrderUseCase: CreateOrderUseCase
    private lateinit var mockMvc: MockMvc

    init {
        this.beforeTest {
            createOrderUseCase = mockk<CreateOrderUseCase> {
                every { createOrder(any()) } returns 1L
            }

            mockMvc = MockMvcBuilders.standaloneSetup(
                CreateOrderController(createOrderUseCase)
            ).build()
        }

        this.describe("POST /api/orders") {

            context("주문 생성을 위해 유효한 데이터가 전달되면") {
                val orderCommand = fixture<CreateOrderCommand>()

                it("201 응답과 주문 번호를 응답해야 한다") {

                    mockMvc.perform(
                        post("/api/orders")
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(orderCommand)))
                        .andDo(print())
                        .andExpect(status().isCreated)
                        .andExpect(jsonPath("$.data").value(1L))

                    verify(exactly = 1) { createOrderUseCase.createOrder(any()) }
                }
            }
        }
    }
}