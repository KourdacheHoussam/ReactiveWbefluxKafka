package com.webflux.kafka.delivery_system.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @author : Houssam KOURDACHE
 */
public record ClientLightDto(String id,
                             @NotEmpty(message = "{validators.client.name.mandatory}")
                             String nom,
                             @Email(message = "Client mail address is not well formed")
                             @NotNull(message = "Client email is mandatory")
                             String email) {
}
