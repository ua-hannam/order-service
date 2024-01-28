package com.uahannam.adapter.persistence

import com.appmattus.kotlinfixture.kotlinFixture
import com.uahannam.common.exception.CustomException
import com.uahannam.common.exception.ErrorCode.*
import com.uahannam.order.adapter.out.persistence.adapter.CreateOrderPersistenceAdapter
import com.uahannam.order.adapter.out.persistence.adapter.LoadOrderPersistenceAdapter
import com.uahannam.order.application.port.`in`.model.CreateOrderCommand
import com.uahannam.order.domain.OrderStatus.*
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import

@DataJpaTest
@Import(LoadOrderPersistenceAdapter::class, CreateOrderPersistenceAdapter::class)
internal class LoadOrderPersistenceAdapterTest(
    val loadOrderPersistenceAdapter: LoadOrderPersistenceAdapter,
    val createOrderPersistenceAdapter: CreateOrderPersistenceAdapter
) : BehaviorSpec({

    val fixture = kotlinFixture()

    Given("주문 데이터를 조회하려고 하는 상황에서(정상 케이스)") {
        val orderCommand = fixture<CreateOrderCommand>()
        val savedOrderId = createOrderPersistenceAdapter.createOrder(orderCommand)

        When("존재하는 주문의 고유 ID를 이용해 조회를 시도하면") {
            val expectedOrder = loadOrderPersistenceAdapter.loadOrderById(savedOrderId)

            Then("조회가 성공해야 한다") {
                expectedOrder.orderer.address shouldBe orderCommand.address
                expectedOrder.orderItem.size shouldBe orderCommand.orderItems.size
                expectedOrder.orderStatus shouldBe RECEIPT
            }
        }
    }

    Given("주문 데이터를 조회하려고 하는 상황에서(비정상 케이스)") {
        val orderCommand = fixture<CreateOrderCommand>()
        createOrderPersistenceAdapter.createOrder(orderCommand)

        When("존재하지 않는 주문의 고유 ID를 이용해 조회를 시도하면") {
            val exception = shouldThrow<CustomException> {
                loadOrderPersistenceAdapter.loadOrderById(0L)
            }

            Then("예외가 발생해야 한다") {
                exception.errorCode shouldBe ORDER_NOT_FOUND
            }
        }
    }

})