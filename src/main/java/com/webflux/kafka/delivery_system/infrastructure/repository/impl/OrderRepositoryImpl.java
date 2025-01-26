package com.webflux.kafka.delivery_system.infrastructure.repository.impl;

import com.webflux.kafka.delivery_system.entity.Order;
import com.webflux.kafka.delivery_system.infrastructure.repository.dao.OrderDAO;
import com.webflux.kafka.delivery_system.repository.OrderRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author : Houssam KOURDACHE
 */
@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderDAO orderDAO;

    public OrderRepositoryImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public Mono<Order> createOrder(Order order) {
        return orderDAO.save(order);
    }

    @Override
    public Flux<Order> allOrders() {
        return null;
    }

    @Override
    public Mono<Order> updateOrder(Order order) {
        return null;
    }

    @Override
    public Flux<Order> clientOrders(String clientId) {
        return null;
    }
}
