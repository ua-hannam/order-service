package com.uahannam.order.adapter.out.persistence.repository


import com.uahannam.order.adapter.out.persistence.entity.OrderJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<OrderJpaEntity, Long>