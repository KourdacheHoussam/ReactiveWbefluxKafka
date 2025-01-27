package com.webflux.kafka.delivery_system.application.dto;

import com.webflux.kafka.delivery_system.entity.OrderStatus;

/**
 * @author : Houssam KOURDACHE
 */
public record OrderLightDto(String id, String clientId, OrderStatus status) {
}
