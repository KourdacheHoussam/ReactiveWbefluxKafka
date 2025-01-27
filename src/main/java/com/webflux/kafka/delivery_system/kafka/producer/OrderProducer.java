package com.webflux.kafka.delivery_system.kafka.producer;

import com.webflux.kafka.delivery_system.application.dto.OrderFullDto;
import com.webflux.kafka.delivery_system.entity.OrderStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

/**
 * @author : Houssam KOURDACHE
 * produce a list of orders in order to forward theme to the frontend by using a text/event-stream
 * endpoint
 */
@Configuration
public class OrderProducer {

    private final AtomicLong counter = new AtomicLong();

    /**
     * this is going to produce a random order each 2sec (like configured in application.yml)
     * '''
     *         createdOrder-in-0:
     *           destination: created-order-topic-in
     *           producer:
     *             poller:
     *               fixed-delay: 2000
     * '''
     * @return
     */
    @Bean
    public Supplier<OrderFullDto> produceOrders() {
        return () -> {
            // Create and return a new OrderFullDto
            return createOrder();
        };
    }

    private final Sinks.Many<OrderFullDto> orderSink = Sinks.many().multicast().directBestEffort();
    private OrderFullDto createOrder() {
        long orderId = counter.incrementAndGet();
        // Create and populate your OrderFullDto here
        OrderFullDto or =  new OrderFullDto(
                String.valueOf(orderId),
                "Client" + orderId,
                Collections.emptyList(),  // Assuming no products for this example
                OrderStatus.CREATED
        );
        orderSink.tryEmitNext(or);
        return or;
    }

    public Flux<OrderFullDto> getRealTimeOrderAsFlux() {
        return orderSink.asFlux().delayElements(Duration.ofMillis(400));
    }

}
