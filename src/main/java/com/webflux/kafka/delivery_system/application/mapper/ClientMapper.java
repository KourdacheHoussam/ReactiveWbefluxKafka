package com.webflux.kafka.delivery_system.application.mapper;

import com.webflux.kafka.delivery_system.application.dto.ClientFullDto;
import com.webflux.kafka.delivery_system.application.dto.ClientLightDto;
import com.webflux.kafka.delivery_system.entity.Client;
import org.mapstruct.Mapper;

/**
 * @author : Houssam KOURDACHE
 */
@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client lightToEntity(ClientLightDto lightDto);

    Client fullToEntity(ClientFullDto clientFullDto);

    ClientFullDto clientToFullDto(Client client);

    ClientLightDto clientToLightDto(Client client);
}
