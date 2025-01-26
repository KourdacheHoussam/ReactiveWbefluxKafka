package com.webflux.kafka.delivery_system.application.service;

import com.webflux.kafka.delivery_system.application.dto.OrderFullDto;
import com.webflux.kafka.delivery_system.application.mapper.OrderMapper;
import com.webflux.kafka.delivery_system.repository.OrderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author : Houssam KOURDACHE
 */
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public Mono<OrderFullDto> createOrder(OrderFullDto orderFullDto) {
        return this.orderRepository.createOrder(orderMapper.fullToEntity(orderFullDto))
                .map(orderMapper::toFullDto);
    }
}
