package com.uahannam.order.adapter.out.persistence.repository


import com.uahannam.order.adapter.out.persistence.entity.OrderJpaEntity
import com.uahannam.order.domain.OrderStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface OrderRepository: JpaRepository<OrderJpaEntity, Long> {

    @Modifying(clearAutomatically = true)
    @Query("update ORDERS o set o.orderStatus = :orderStatus where o.orderId = :orderId")
    fun modifyOrderStatus(@Param("orderStatus") orderStatus: OrderStatus, @Param("orderId") orderId: Long)
}