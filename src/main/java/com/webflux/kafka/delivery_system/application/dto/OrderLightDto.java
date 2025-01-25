package com.webflux.kafka.delivery_system.application.dto;

import com.webflux.kafka.delivery_system.entity.OrderStatus;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author : Houssam KOURDACHE
 */
public record OrderLightDto(String id, String clientId, OrderStatus status) {
}
