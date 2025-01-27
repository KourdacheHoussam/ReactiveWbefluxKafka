package com.webflux.kafka.delivery_system.application.service;

import com.webflux.kafka.delivery_system.application.dto.OrderCommentsDto;
import com.webflux.kafka.delivery_system.application.dto.OrderFullDto;
import com.webflux.kafka.delivery_system.application.mapper.OrderMapper;
import com.webflux.kafka.delivery_system.application.service.external.CommentService;
import com.webflux.kafka.delivery_system.entity.Order;
import com.webflux.kafka.delivery_system.kafka.producer.OrderProducer;
import com.webflux.kafka.delivery_system.repository.OrderRepository;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author : Houssam KOURDACHE
 */
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderProducer orderProducer;
    private final CommentService commentService;

    public OrderService(OrderRepository orderRepository,
                        OrderMapper orderMapper,
                        StreamBridge streamBridge,
                        OrderProducer orderProducer,
                        CommentService commentService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.orderProducer = orderProducer;
        this.commentService = commentService;
    }

    public Mono<OrderFullDto> createOrder(OrderFullDto orderFullDto) {
        return this.orderRepository.createOrder(orderMapper.fullToEntity(orderFullDto))
                .map(orderMapper::toFullDto);
    }

    public Flux<OrderFullDto> getClientOrders(String clientId) {
        return this.orderRepository.findByClientId(clientId).map(orderMapper::toFullDto);
    }

    public Flux<OrderFullDto> realTimeOrders() {
        return orderProducer.getRealTimeOrderAsFlux();
    }

    public Mono<OrderCommentsDto> getOrderComment(Long orderId) {
        return null;
        /**
         *
         * @TODO
         *
         return this.orderRepository.allOrders().map(Order::id)
         .single()
         .map(id -> {
         Mono.zip(this.orderRepository.findByClientId(id),
         this.commentService.getOrderComment(1L)
         .map(commentDto -> commentDto)
         .collectList(),
         (order, comments)-> {
         return new OrderCommentsDto(
         orderMapper.toLightDto((Order) order),
         comments
         );
         });
         });
         *
         */

    }
}
