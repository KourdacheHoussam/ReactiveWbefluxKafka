package com.webflux.kafka.delivery_system.application.dto;

import com.webflux.kafka.delivery_system.entity.DeliveryStatus;

/**
 * @author : Houssam KOURDACHE
 */
public record DeliveryFullDto(String id, OrderLightDto order, DeliveryStatus deliveryStatus) {

}
