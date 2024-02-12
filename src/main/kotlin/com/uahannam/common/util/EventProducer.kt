package com.uahannam.common.util

import com.uahannam.common.annotation.Util
import org.springframework.context.ApplicationEventPublisher

//@Util
class EventProducer private constructor(
//    applicationEventPublisher: ApplicationEventPublisher
) {

//    init {
//        eventPublisher = applicationEventPublisher
//    }

    companion object {
        private lateinit var eventPublisher: ApplicationEventPublisher

        fun produceEvent(event: Any) {
            eventPublisher.publishEvent(event)
        }

        fun setEventPublisher(eventPublisher: ApplicationEventPublisher) {
            this.eventPublisher = eventPublisher
        }
    }
}