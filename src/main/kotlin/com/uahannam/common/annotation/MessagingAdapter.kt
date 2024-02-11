package com.uahannam.common.annotation

import org.springframework.stereotype.Component

@Component
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class MessagingAdapter()
