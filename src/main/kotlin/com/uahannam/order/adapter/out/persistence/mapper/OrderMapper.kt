package com.uahannam.order.adapter.out.persistence.mapper

import com.uahannam.order.adapter.out.messaging.produce.dto.OrderEvent
import com.uahannam.order.adapter.out.messaging.produce.dto.OrderEventDto
import com.uahannam.order.adapter.out.messaging.produce.dto.OrderItemEventDto
import com.uahannam.order.adapter.out.persistence.entity.OrderItemJpaEntity
import com.uahannam.order.adapter.out.persistence.entity.OrderJpaEntity
import com.uahannam.order.domain.Order
import com.uahannam.order.domain.OrderInfo
import com.uahannam.order.domain.OrderItem
import com.uahannam.order.domain.Orderer


abstract class OrderMapper private constructor(){
    companion object {

        fun mapToDomainEntity(orderJpaEntity: OrderJpaEntity, orderItemListEntity: List<OrderItemJpaEntity>) : Order {
            val orderInfo = OrderInfo(
                orderId = orderJpaEntity.orderId!!,
                storedId = orderJpaEntity.storeId,
                totalPrice = orderJpaEntity.totalPrice,
                delStatus = orderJpaEntity.delStatus,
                orderStatus = orderJpaEntity.orderStatus
            )

            val orderItemList = orderItemListEntity.map {
                OrderItem(
                    itemName = it.itemName,
                    itemPrice = it.itemPrice,
                    itemQuantity = it.itemQuantity
                )
            }.toList()

            val orderer = Orderer(
                memberId = orderJpaEntity.memberId,
                name = "Dev Seo Rex",
                address = orderJpaEntity.address,
                phoneNumber = "010-1111-1111"
            )

            return Order(orderInfo, orderer, orderItemList)
        }

        fun mapToOrderEventDto(orderJpaEntity: OrderJpaEntity) : OrderEventDto {
            return OrderEventDto(
                orderId = orderJpaEntity.orderId!!,
                memberId = orderJpaEntity.memberId,
                address = orderJpaEntity.address,
                storeId = orderJpaEntity.storeId,
                totalPrice = orderJpaEntity.totalPrice,
                orderStatus = orderJpaEntity.orderStatus,
                delStatus = orderJpaEntity.delStatus,
                regDate = orderJpaEntity.regDate!!,
                modDate = orderJpaEntity.modDate!!
            )
        }

        fun mapToOrderItemEventDto(orderItemJpaEntityList: List<OrderItemJpaEntity>) : List<OrderItemEventDto> {
            return orderItemJpaEntityList
                .map {
                    OrderItemEventDto(
                        orderItemId = it.orderItemId!!,
                        orderId = it.orderId,
                        itemId = it.itemId,
                        itemPrice = it.itemPrice,
                        itemName = it.itemName,
                        itemQuantity = it.itemQuantity,
                        itemTotalPrice = it.itemTotalPrice,
                        delStatus = it.delStatus,
                        regDate = it.regDate!!,
                        modDate = it.modDate!!
                    )
                }.toList()
        }
    }
}