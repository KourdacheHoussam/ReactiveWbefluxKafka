package com.webflux.kafka.delivery_system.application.dto;

import java.util.List;

/**
 * @author : Houssam KOURDACHE
 */
public record ClientFullDto(String id, String nom, String email, String address, List<ProductLightDto> products) {
}

