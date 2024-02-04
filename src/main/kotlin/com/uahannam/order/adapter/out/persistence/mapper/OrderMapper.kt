package com.uahannam.order.adapter.out.persistence.mapper

import com.uahannam.common.annotation.Mapper
import com.uahannam.order.adapter.out.persistence.entity.OrderItemJpaEntity
import com.uahannam.order.adapter.out.persistence.entity.OrderJpaEntity
import com.uahannam.order.domain.Order
import com.uahannam.order.domain.OrderInfo
import com.uahannam.order.domain.OrderItem
import com.uahannam.order.domain.Orderer

@Mapper
class OrderMapper {

    fun mapToJpaEntity(order: Order) : OrderJpaEntity {
        return OrderJpaEntity(
            orderId = order.orderInfo.orderId,
            memberId = order.orderer.memberId,
            address = order.orderer.address,
            storeId = order.orderInfo.storedId,
            totalPrice = order.orderInfo.totalPrice,
            orderStatus = order.orderInfo.orderStatus,
            delStatus = order.orderInfo.delStatus
        )
    }

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