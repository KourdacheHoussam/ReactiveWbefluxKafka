package com.webflux.kafka.delivery_system.rest;

import com.webflux.kafka.delivery_system.application.dto.OrderFullDto;
import com.webflux.kafka.delivery_system.application.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author : Houssam KOURDACHE
 */
@Validated
@RestController
@RequestMapping("/orders")
@Tag(name = "Order CRUD operations")
public class OrderController {

    private final OrderService orderService;
    private final StreamBridge streamBridge;


    public OrderController(OrderService orderService, StreamBridge streamBridge) {
        this.orderService = orderService;
        this.streamBridge = streamBridge;
    }

    @PostMapping("/add")
    @Operation(summary = "Create a client's order")
    @ApiResponses()
    public Mono<OrderFullDto> createOrder(@Valid @RequestBody OrderFullDto orderFullDto) {
        return orderService.createOrder(orderFullDto);
    }

    @PostMapping("/client/{clientId}")
    @Operation(summary = "Get client's orders")
    @ApiResponses()
    public Flux<OrderFullDto> getClientOrders(@PathVariable("clientId") String clientId) {
        return orderService.getClientOrders(clientId);
    }

    @PostMapping("/publish")
    @Operation(summary = "Publish an order to a topic: created-order-topic-in")
    @ApiResponses()
    public String publishOrder(@RequestBody OrderFullDto orderFullDto) {
        streamBridge.send("created-order-topic-in", orderFullDto);
        return "Order published successfully";
    }

    @GetMapping(value = "/realTimeOrders", produces = "text/event-stream")
    @Operation(summary = "Listen for all orders published by kafka on topic 'produce-orders-topic-out'")
    @ApiResponses()
    public Flux<OrderFullDto> realTimeOrders() {
        return orderService.realTimeOrders();
    }

}
