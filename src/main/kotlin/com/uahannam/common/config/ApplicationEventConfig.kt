package com.uahannam.common.config

import com.uahannam.common.util.EventProducer
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener

@Configuration
class ApplicationEventConfig(
    private val applicationEventPublisher: ApplicationEventPublisher
) {

    @EventListener(ApplicationReadyEvent::class)
    fun initializeEventPublisher() {
        EventProducer.setEventPublisher(applicationEventPublisher)
    }

}