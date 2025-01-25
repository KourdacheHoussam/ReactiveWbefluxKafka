package com.webflux.kafka.delivery_system.entity;

import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

/**
 * @author : Houssam KOURDACHE
 */
@Document("order")
public record Order(String id, String clientId, List<Product> products, OrderStatus status) {
    public Order(String id) {
        this(UUID.randomUUID().toString(), "", List.of(), OrderStatus.CREATED);
    }
}
