package com.webflux.kafka.delivery_system.application.service;

import com.webflux.kafka.delivery_system.application.dto.OrderFullDto;
import com.webflux.kafka.delivery_system.application.mapper.OrderMapper;
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
    private final StreamBridge streamBridge;
    private final OrderProducer orderProducer;

    public OrderService(OrderRepository orderRepository,
                        OrderMapper orderMapper,
                        StreamBridge streamBridge,
                        OrderProducer orderProducer) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.streamBridge = streamBridge;
        this.orderProducer = orderProducer;
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
}
