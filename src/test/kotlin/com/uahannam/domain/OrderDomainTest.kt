package com.uahannam.domain

import com.appmattus.kotlinfixture.kotlinFixture
import com.uahannam.common.exception.CustomException
import com.uahannam.common.exception.ErrorCode
import com.uahannam.order.domain.Order
import com.uahannam.order.domain.OrderStatus
import com.uahannam.order.domain.OrderStatus.*
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

private val fixture = kotlinFixture()

class OrderDomainTest : BehaviorSpec({


        Given("배달 주문 접수 취소가 가능한 상황에서") {
            val order = fixture<Order> {
                property<Order, OrderStatus>("orderStatus") { RECEIPT }
            }


            When("배달 주문 접수 취소 시도를 하면") {
                order.requestCancel()


                Then("정상적으로 취소 요청이 접수되어야 한다") {
                    order.orderStatus shouldBe USER_CANCEL_REQUEST
                }
            }
        }


        Given("배달 주문 접수 취소가 불가능 한 상황에서") {
            val order = fixture<Order> {
                property<Order, OrderStatus>("orderStatus") { COOKING }
            }

            When("배달 주문 접수 취소 시도를 하면") {
                val exception = shouldThrow<CustomException> {
                    order.requestCancel()
                }

                Then("취소가 되지 않아야 한다") {
                    exception.errorCode shouldBe ErrorCode.CANNOT_CANCEL_ORDER
                }
            }
        }

})

