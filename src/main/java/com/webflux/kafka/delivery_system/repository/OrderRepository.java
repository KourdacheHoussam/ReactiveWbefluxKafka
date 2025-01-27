package com.webflux.kafka.delivery_system.repository;

import com.webflux.kafka.delivery_system.entity.Order;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author : Houssam KOURDACHE
 */
public interface OrderRepository {
    Mono<Order> createOrder(Order order);

    Flux<Order> findByClientId(String clientId);

    Flux<Order> allOrders();

    Mono<Order> updateOrder(Order order);

    Flux<Order> clientOrders(String clientId);
}
