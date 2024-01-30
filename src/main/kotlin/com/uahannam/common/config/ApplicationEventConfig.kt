package com.uahannam.common.config

import com.uahannam.order.utils.EventProducer
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationEventConfig(
    private val applicationContext: ApplicationContext
) {

    @Bean
    fun eventProducerInitializer() : () -> Unit {
        return { EventProducer.initializeEventPublisher(applicationContext) }
    }
}