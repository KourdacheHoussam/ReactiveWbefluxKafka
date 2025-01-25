package com.webflux.kafka.delivery_system.application.dto;

import com.webflux.kafka.delivery_system.entity.OrderStatus;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author : Houssam KOURDACHE
 */
public record OrderFullDto(String id, String clientId, List<ProductFullDto> productFullDtos, OrderStatus status) {
}
