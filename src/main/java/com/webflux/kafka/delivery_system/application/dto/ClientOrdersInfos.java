package com.webflux.kafka.delivery_system.application.dto;

import java.util.List;

/**
 * @author : Houssam KOURDACHE
 */
public record ClientOrdersInfos(ClientFullDto client, List<OrderFullDto> orders) {

}
