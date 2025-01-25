package com.webflux.kafka.delivery_system.application.dto;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author : Houssam KOURDACHE
 */
public record ProductFullDto(String id, String name, Double weight, String description) {
}
