package com.webflux.kafka.delivery_system.application.dto;

import com.webflux.kafka.delivery_system.entity.OrderStatus;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * @author : Houssam KOURDACHE
 */
public record OrderFullDto(String id,
                           @NotNull String clientId,
                           List<ProductFullDto> products,
                           @NotNull OrderStatus status) {
}
