package com.uahannam.order.config

import com.uahannam.order.handler.OrderHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router

@Configuration
class RouterConfig {

    @Bean
    fun routerFunction(orderHandler: OrderHandler) = router {
        accept(MediaType.APPLICATION_JSON).nest {
            POST("/api/orders", orderHandler::saveOrder)
            GET("/api/test/orders", orderHandler::findAllOrders)
            GET("/api/test/orderItems", orderHandler::findAllOrderItems)
            GET("/api/test/orderItems/{orderId}", orderHandler::findOrderItemsByOrderId)
            PATCH("/api/orders/{orderId}", orderHandler::modifyOrderStatusByOrderId)
        }
    }
}