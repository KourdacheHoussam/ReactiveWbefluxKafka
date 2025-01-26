package com.webflux.kafka.delivery_system.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author : Houssam KOURDACHE
 */
@Document("client")
public record Client(@Id String id, String nom, String email, String address, List<Order> orders) {
}
