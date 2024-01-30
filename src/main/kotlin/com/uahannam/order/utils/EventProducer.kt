package com.uahannam.order.utils

import org.springframework.context.ApplicationEventPublisher

class EventProducer {

    companion object {
        private lateinit var eventPublisher: ApplicationEventPublisher

        fun initializeEventPublisher(eventPublisher: ApplicationEventPublisher) {
            this.eventPublisher = eventPublisher
        }

        fun produceEvent(event: Any) {
            eventPublisher.publishEvent(event)
        }
    }
}