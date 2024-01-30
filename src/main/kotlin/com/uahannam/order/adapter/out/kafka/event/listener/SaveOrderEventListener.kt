package com.uahannam.order.adapter.out.kafka.event.listener

import com.uahannam.order.adapter.out.kafka.event.dto.SaveOrderEventDto
import com.uahannam.order.adapter.out.kafka.produce.dto.Order
import com.uahannam.order.adapter.out.kafka.produce.dto.OrderEvent
import com.uahannam.order.adapter.out.kafka.produce.dto.OrderItem
import com.uahannam.order.adapter.out.kafka.produce.dto.SaveOrderKafkaDto
import org.springframework.context.event.EventListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class SaveOrderEventListener(
    private val saveOrderKafkaTemplate: KafkaTemplate<String, SaveOrderKafkaDto>
) {

    @Async
    @EventListener(SaveOrderEventDto::class)
    fun handleSaveEvent(orderEventDto: SaveOrderEventDto) {

        // ReadModel 이벤트 발행 -> 비동기 처리
        saveOrderKafkaTemplate.send("save-order-data", createSaveOrderKafkaDto(orderEventDto))
    }


    private fun createSaveOrderKafkaDto(orderEventDto: SaveOrderEventDto) : SaveOrderKafkaDto {
        val order = Order(
            orderId = orderEventDto.orderJpaEntity.orderId!!,
            memberId = orderEventDto.orderJpaEntity.memberId,
            address = orderEventDto.orderJpaEntity.address,
            storeId = orderEventDto.orderJpaEntity.storeId,
            totalPrice = orderEventDto.orderJpaEntity.totalPrice,
            orderStatus = orderEventDto.orderJpaEntity.orderStatus,
            regDate = orderEventDto.orderJpaEntity.regDate!!,
            modDate = orderEventDto.orderJpaEntity.modDate!!
        )

        val orderItemList = orderEventDto.orderItemJpaEntityList
            .map {
                OrderItem(
                    orderItemId = it.orderItemId!!,
                    orderId = it.orderId,
                    itemId = it.itemId,
                    itemPrice = it.itemPrice,
                    itemName = it.itemName,
                    itemQuantity = it.itemQuantity,
                    itemTotalPrice = it.itemTotalPrice,
                    regDate = it.regDate!!,
                    modDate = it.modDate!!
                )
            }.toList()

        val orderEvent = OrderEvent(
            eventUUID = UUID.randomUUID().toString(),
            orderId = order.orderId
        )

        return SaveOrderKafkaDto(order, orderItemList, orderEvent)
    }
}