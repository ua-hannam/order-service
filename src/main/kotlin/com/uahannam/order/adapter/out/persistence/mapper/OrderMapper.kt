package com.uahannam.order.adapter.out.persistence.mapper

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
    }
}