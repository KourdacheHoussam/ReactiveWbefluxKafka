package com.webflux.kafka.delivery_system.application.dto;

/**
 * @author : Houssam KOURDACHE
 */
public record CommentDto(Long postId, Long id, String name, String email, String body) {
}
