package com.uahannam.adapter.web

import com.appmattus.kotlinfixture.kotlinFixture
import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.uahannam.order.adapter.`in`.web.CreateOrderController
import com.uahannam.order.application.port.`in`.model.CreateOrderCommand
import com.uahannam.order.application.port.`in`.usecase.CreateOrderUseCase
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.verify
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.*
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
internal class CreateOrderControllerTest(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper,
    @MockkBean
    private val createOrderUseCase: CreateOrderUseCase
) : DescribeSpec({

    val fixture = kotlinFixture()

        describe("주문 생성 컨트롤러 테스트") {

            it("주문을 생성하면 201 응답과 주문 번호를 응답해야 한다") {
                val orderCommand = fixture<CreateOrderCommand>()
                every { createOrderUseCase.createOrder(orderCommand) } returns 1L

                val result = mockMvc.perform(
                    post("/api/orders")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderCommand)))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isCreated)
                    .andReturn()

                val responseBody = result.response.contentAsString.toLong()

                responseBody shouldBe 1L
                verify(exactly = 1) { createOrderUseCase.createOrder(orderCommand) }
            }
        }
})