package com.webflux.kafka.delivery_system.application.mapper;

import com.webflux.kafka.delivery_system.application.dto.DeliveryFullDto;
import com.webflux.kafka.delivery_system.application.dto.DeliveryLightDto;
import com.webflux.kafka.delivery_system.entity.Delivery;
import org.mapstruct.Mapper;

/**
 * @author : Houssam KOURDACHE
 */
@Mapper(componentModel = "spring")
public interface DeliveryMapper {
    DeliveryFullDto toDeliveryFullDto(Delivery delivery);
    DeliveryLightDto toDeliveryLightDto(Delivery delivery);
    Delivery toEntity(DeliveryFullDto deliveryFullDto);
    Delivery toLight(DeliveryLightDto deliveryLightDto);
}
