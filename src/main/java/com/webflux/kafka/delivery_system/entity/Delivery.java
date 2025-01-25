package com.webflux.kafka.delivery_system.entity;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author : Houssam KOURDACHE
 */
@Document("document")
public record Delivery(String id, Order order, DeliveryStatus deliveryStatus) {
}
