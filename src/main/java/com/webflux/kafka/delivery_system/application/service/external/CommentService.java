package com.webflux.kafka.delivery_system.application.service.external;

import com.webflux.kafka.delivery_system.application.dto.CommentDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * @author : Houssam KOURDACHE
 */
@Service
public class CommentService {

    /**
     * call external api to get comments of an order
     */
    public Flux<CommentDto> getOrderComment(Long orderId) {
        return WebClient.create("https://jsonplaceholder.typicode.com")
                .get()
                .uri(URI.create("/posts/" + orderId))
                .retrieve()
                .bodyToFlux(CommentDto.class);
    }

}
