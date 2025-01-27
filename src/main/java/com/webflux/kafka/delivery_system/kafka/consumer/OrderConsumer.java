package com.webflux.kafka.delivery_system.kafka.consumer;

import com.webflux.kafka.delivery_system.application.dto.ClientFullDto;
import com.webflux.kafka.delivery_system.application.dto.OrderFullDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

/**
 * @author : Houssam KOURDACHE
 */
@Configuration
public class OrderConsumer {

    @Bean
    public Consumer<OrderFullDto> createdOrder() {
        return o -> System.out.println("Created order : " + o);
    }

    @Bean
    public Consumer<ClientFullDto> createdClient() {
        return c -> System.out.println("Created client : " + c);
    }
}
