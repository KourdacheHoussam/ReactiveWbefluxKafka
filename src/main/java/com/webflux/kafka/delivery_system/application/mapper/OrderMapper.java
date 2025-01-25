package com.webflux.kafka.delivery_system.application.mapper;

import com.webflux.kafka.delivery_system.application.dto.ProductFullDto;
import com.webflux.kafka.delivery_system.application.dto.ProductLightDto;
import com.webflux.kafka.delivery_system.entity.Product;
import org.mapstruct.Mapper;

/**
 * @author : Houssam KOURDACHE
 */
@Mapper(componentModel = "spring")
public interface OrderMapper {

    ProductFullDto toFullDto(Product product);
    ProductLightDto toLightDto(Product product);
    Product lightToEntity(ProductLightDto lightDto);
    Product fullToEntity(ProductFullDto fullDto);
}
