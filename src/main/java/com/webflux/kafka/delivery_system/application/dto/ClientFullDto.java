package com.webflux.kafka.delivery_system.application.dto;

import com.webflux.kafka.delivery_system.entity.Product;

import java.util.List;

/**
 * @author : Houssam KOURDACHE
 */
public record ClientFullDto(String id, String nom, String email, String address, List<Product> products) {
}

