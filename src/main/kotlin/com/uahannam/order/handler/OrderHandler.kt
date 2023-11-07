package com.uahannam.order.handler

import com.uahannam.order.domain.request.OrderCreateRequestDto
import com.uahannam.order.domain.request.OrderModifyRequestDto
import com.uahannam.order.domain.statusEnum.OrderStatus
import com.uahannam.order.service.OrderItemService
import com.uahannam.order.service.OrderService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

@Component
class OrderHandler(
        private val orderService: OrderService,
        private val orderItemService: OrderItemService
) {


    fun saveOrder(serverRequest: ServerRequest) =
            serverRequest.bodyToMono(OrderCreateRequestDto::class.java)
                    .flatMap {
                        requestDto ->
                        orderService.saveOrder(requestDto, 1L)
                                .flatMap {
                                    order ->
                                    orderItemService.saveOrderItems(order.orderId, requestDto.orderItems)
                                    ServerResponse
                                            .ok().build()
                                }
                    }


    fun findAllOrders(serverRequest: ServerRequest) =
            orderService.findAllOrders()
                    .collectList()
                    .flatMap {
                        ServerResponse
                                .ok()
                                .body(BodyInserters.fromValue(it))
                    }


    fun findAllOrderItems(serverRequest: ServerRequest) =
            orderItemService.findAllOrderItems()
                    .collectList()
                    .flatMap {
                        ServerResponse
                                .ok()
                                .body(BodyInserters.fromValue(it))
                    }

    fun findOrderItemsByOrderId(serverRequest: ServerRequest) =
            orderItemService.findAllOrderItemsByOrderId(
                    serverRequest.pathVariable("orderId").toLong())
                    .collectList()
                    .flatMap {
                        ServerResponse
                                .ok()
                                .body(BodyInserters.fromValue(it))
                    }

    fun modifyOrderStatusByOrderId(serverRequest: ServerRequest) =
            serverRequest.bodyToMono(OrderModifyRequestDto::class.java)
                    .flatMap {
                        orderService.modifyOrderStatus(
                                it.orderStatus, serverRequest.pathVariable("orderId").toLong())
                        ServerResponse
                                .ok().build()
                    }
}