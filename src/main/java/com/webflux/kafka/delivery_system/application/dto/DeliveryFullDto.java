package com.webflux.kafka.delivery_system.application.dto;

import com.webflux.kafka.delivery_system.entity.DeliveryStatus;
import com.webflux.kafka.delivery_system.entity.Order;

/**
 * @author : Houssam KOURDACHE
 */
public record DeliveryFullDto(String id, Order order, DeliveryStatus deliveryStatus) {

}
