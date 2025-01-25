package com.webflux.kafka.delivery_system.infrastructure.repository.dao;

import com.webflux.kafka.delivery_system.entity.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * @author : Houssam KOURDACHE
 */
@Repository
public interface OrderDAO extends ReactiveMongoRepository<Order, String> {

    Flux<Order> findByClientId(String clientId);
}
