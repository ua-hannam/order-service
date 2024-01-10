package com.uahannam.order.adapter.out.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
class DateBaseEntity(
    @CreatedDate
    @Column(name = "REG_DATE")
    val regDate: LocalDateTime? = null,

    @LastModifiedDate
    @Column(name = "MOD_DATE")
    val modDate: LocalDateTime? = null
)