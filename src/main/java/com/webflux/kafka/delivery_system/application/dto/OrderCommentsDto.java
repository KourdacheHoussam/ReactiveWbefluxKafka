package com.webflux.kafka.delivery_system.application.dto;

import java.util.List;

/**
 * @author : Houssam KOURDACHE
 */
public record OrderCommentsDto(OrderLightDto order, List<CommentDto> comments) {
}
