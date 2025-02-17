package com.webflux.kafka.delivery_system.application.dto;

import java.util.List;

/**
 *
 * @author : Houssam KOURDACHE
 */
public record ClientOrdersDto(ClientLightDto client, List<OrderFullDto> orders) {}
