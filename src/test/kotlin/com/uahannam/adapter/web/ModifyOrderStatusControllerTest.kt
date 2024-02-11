package com.uahannam.adapter.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.uahannam.common.dto.base.BaseResponse
import com.uahannam.common.exception.CustomException
import com.uahannam.common.exception.ErrorCode.*
import com.uahannam.common.exception.GlobalCatcher
import com.uahannam.order.adapter.`in`.web.ModifyOrderStatusController
import com.uahannam.order.application.port.`in`.usecase.ModifyOrderStatusUseCase
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders.*

@SpringBootTest(classes = [ObjectMapper::class])
class ModifyOrderStatusControllerTest(
    private val objectMapper: ObjectMapper
) : DescribeSpec() {

    private lateinit var modifyOrderStatusUseCase: ModifyOrderStatusUseCase
    private lateinit var mockMvc: MockMvc

    init {

        this.describe("PATCH /api/orders/{orderId} -> 정상 케이스") {

            modifyOrderStatusUseCase = mockk {
                every { modifyOrderStatus(any()) } returns 1L
            }

            mockMvc = standaloneSetup(
                ModifyOrderStatusController(modifyOrderStatusUseCase)
            ).build()

            context("주문 취소 가능 상태에서, 주문 취소 요청을 위해 PK가 전달되면") {

                val orderId = 1L
                val responseOrder = mockMvc.perform(
                    patch("/api/orders/$orderId")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk)
                    .andReturn()
                    .response.contentAsString

                val expectedResponse = objectMapper.writeValueAsString(BaseResponse(orderId))


                it("취소 요청이 성공해야 한다") {
                    responseOrder shouldBe expectedResponse
                    verify(exactly = 1) { modifyOrderStatusUseCase.modifyOrderStatus(any()) }
                }
            }
        }

        this.describe("PATCH /api/orders/{orderId} -> 비정상 케이스") {

            modifyOrderStatusUseCase = mockk {
                every { modifyOrderStatus(any()) } throws CustomException(CANNOT_CANCEL_ORDER)
            }

            mockMvc = standaloneSetup(
                ModifyOrderStatusController(modifyOrderStatusUseCase)
            ).setControllerAdvice(GlobalCatcher())
            .build()

            context("주문 취소 불가능 상태에서, 주문 취소 요청을 위해 PK가 전달되면") {
                val orderId = 1L

                it("예외가 발생해야 한다") {
                    mockMvc.perform(
                        patch("/api/orders/$orderId")
                            .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isBadRequest)
                        .andExpect(jsonPath("$.serial").value("ERROR_02"))

                    verify(exactly = 1) { modifyOrderStatusUseCase.modifyOrderStatus(any()) }
                }
            }
        }
    }
}