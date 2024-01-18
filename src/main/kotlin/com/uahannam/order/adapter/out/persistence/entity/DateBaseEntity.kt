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
abstract class DateBaseEntity(
    @CreatedDate
    @Column(name = "REG_DATE")
    var regDate: LocalDateTime? = null,

    @LastModifiedDate
    @Column(name = "MOD_DATE")
    var modDate: LocalDateTime? = null
)