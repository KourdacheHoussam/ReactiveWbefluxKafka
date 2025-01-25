package com.webflux.kafka.delivery_system.entity;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author : Houssam KOURDACHE
 */
@Document("product")
public record Product(String id, String name, Double weight, String description) {
}
