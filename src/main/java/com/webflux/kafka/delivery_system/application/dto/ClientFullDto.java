package com.webflux.kafka.delivery_system.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * @author : Houssam KOURDACHE
 */
@Validated
public record ClientFullDto(String id,
                            @NotNull(message = "Client name is mandatory")
                            @NotEmpty(message = "Client name is mandatory")
                            String nom,
                            @Email(message = "Client mail address is not well formed")
                            @NotNull(message = "Client email is mandatory")
                            String email,
                            String address,
                            List<OrderFullDto> orders) {
}

