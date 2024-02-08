package com.uahannam.adapter.persistence

import com.appmattus.kotlinfixture.kotlinFixture
import com.uahannam.common.util.EventProducer
import com.uahannam.order.adapter.out.persistence.adapter.CreateOrderPersistenceAdapter
import com.uahannam.order.adapter.out.persistence.repository.OrderItemRepository
import com.uahannam.order.adapter.out.persistence.repository.OrderRepository
import com.uahannam.order.application.port.`in`.model.CreateOrderCommand
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import

@DataJpaTest
@Import(
    CreateOrderPersistenceAdapter::class,
    EventProducer::class
)
internal class CreateOrderPersistenceAdapterTest(
    private val orderRepository: OrderRepository,
    private val orderItemRepository: OrderItemRepository,
    private val createOrderPersistenceAdapter: CreateOrderPersistenceAdapter
) : BehaviorSpec({

    val fixture = kotlinFixture()

    Given("주문 데이터를 생성하려는 상황에서") {

        val orderCommand = fixture<CreateOrderCommand>()


        When("주문과 주문 상품을 저장하면") {
            val savedOrderId = createOrderPersistenceAdapter.createOrder(orderCommand)

            Then("저장이 되어야 한다") {
                val savedOrderItems = orderItemRepository.findByOrderId(savedOrderId)
                val savedOrder = orderRepository.findById(savedOrderId).get()

                // 저장된 주문 상품들의 개수와 입력된 주문 상품의 개수를 검증
                savedOrderItems.size shouldBe orderCommand.orderItems.size

                // 저장된 주문과 입력된 주문의 데이터를 검증
                savedOrder.totalPrice shouldBe orderCommand.totalPrice
                savedOrder.storeId shouldBe orderCommand.storeId
                savedOrder.address shouldBe orderCommand.address
                savedOrder.memberId shouldBe 1L
            }
        }
    }

})