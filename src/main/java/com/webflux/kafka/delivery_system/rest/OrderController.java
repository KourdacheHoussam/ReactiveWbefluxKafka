package com.webflux.kafka.delivery_system.rest;

import com.webflux.kafka.delivery_system.application.dto.OrderFullDto;
import com.webflux.kafka.delivery_system.application.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @author : Houssam KOURDACHE
 */
@RestController
@RequestMapping("/orders")
@Tag(name = "Order CRUD operations")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add")
    @Operation()
    @ApiResponses()
    public Mono<OrderFullDto> createOrder(@RequestBody OrderFullDto orderFullDto) {
        return orderService.createOrder(orderFullDto);
    }
}
