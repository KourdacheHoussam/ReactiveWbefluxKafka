package com.webflux.kafka.delivery_system.application.dto;

import lombok.Builder;

import java.util.List;

/**
 *
 * @author : Houssam KOURDACHE
 */
@Builder
public record ClientOrdersDto(ClientLightDto client, List<OrderFullDto> orders) {}
