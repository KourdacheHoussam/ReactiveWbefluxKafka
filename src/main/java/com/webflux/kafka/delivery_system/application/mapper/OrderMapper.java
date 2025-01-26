package com.webflux.kafka.delivery_system.application.mapper;

import com.webflux.kafka.delivery_system.application.dto.OrderFullDto;
import com.webflux.kafka.delivery_system.application.dto.OrderLightDto;
import com.webflux.kafka.delivery_system.entity.Order;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author : Houssam KOURDACHE
 */
@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderFullDto toFullDto(Order order);

    List<OrderFullDto> toListFullDto(List<Order> orders);

    OrderLightDto toLightDto(Order order);

    Order lightToEntity(OrderLightDto orderLightDto);

    Order fullToEntity(OrderFullDto orderFullDto);
}
