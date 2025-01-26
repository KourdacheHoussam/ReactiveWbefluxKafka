package com.webflux.kafka.delivery_system.rest;

import com.webflux.kafka.delivery_system.application.dto.ClientFullDto;
import com.webflux.kafka.delivery_system.application.dto.ClientLightDto;
import com.webflux.kafka.delivery_system.application.dto.ClientOrdersDto;
import com.webflux.kafka.delivery_system.application.service.ClientService;
import com.webflux.kafka.delivery_system.entity.Order;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

/**
 * @author : Houssam KOURDACHE
 */
@RestController
@RequestMapping("/clients")
@Tag(name = "Client CRUD operations", description = "Manage client and his orders")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{clientId}")
    @Operation(summary = "Get client information buy his ID",
            description = "Expecting a string id of the client and returning a full dto object containing info of the client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client information retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Client with given ID doesn't exists")
    })
    public Mono<ClientFullDto> getClient(@PathVariable("clientId") String clientId) {
        return clientService.getClient(clientId);
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new client", description = "Mandatory client information, nom and email")
    @ApiResponses()
    public ResponseEntity<Mono<ClientFullDto>> createClient(@RequestBody ClientLightDto clientLightDto) {
        return ResponseEntity.ok(clientService.createClient(clientLightDto));
    }

    @GetMapping(value = "/stream-list", produces = "text/event-stream")
    @Operation(summary = "Fetch all clients", description = "Fetch all clients by chunk")
    @ApiResponses()
    public ResponseEntity<Flux<ClientFullDto>> allClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @DeleteMapping(value = "/{clientId}/delete")
    @Operation(summary = "Delete client by id", description = "Delete client by id")
    @ApiResponses()
    public ResponseEntity<Mono<Boolean>> deleteClientById(@PathVariable("clientId") String clientId) {
        return ResponseEntity.ok(this.clientService.deleteClientById(clientId));
    }

    @DeleteMapping(value = "/delete/list")
    @Operation(summary = "Delete a bunch of clients", description = "Receive a list of clients ids to " +
            "delete, and return the list of deleted ids")
    @ApiResponses()
    public ResponseEntity<Flux<String>> deleteListOfClientByIds(@RequestParam("ClientIds") String [] clientIds) {
        return ResponseEntity.accepted().body(this.clientService.deleteClientsById(clientIds));
    }

    @GetMapping(value = "/andOrders")
    @Operation(summary="Clients orders", description = "Retrieve for each client his list of orders")
    @ApiResponses()
    public ResponseEntity<Flux<ClientOrdersDto>> clientsOrders() {
        return ResponseEntity.ok(this.clientService.getClientsOrders());
    }

}
