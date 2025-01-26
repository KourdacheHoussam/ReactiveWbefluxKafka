package com.webflux.kafka.delivery_system.application.service;

import com.webflux.kafka.delivery_system.application.dto.ClientFullDto;
import com.webflux.kafka.delivery_system.application.dto.ClientLightDto;
import com.webflux.kafka.delivery_system.application.dto.ClientOrdersDto;
import com.webflux.kafka.delivery_system.application.mapper.ClientMapper;
import com.webflux.kafka.delivery_system.application.mapper.OrderMapper;
import com.webflux.kafka.delivery_system.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

/**
 * @author : Houssam KOURDACHE
 */
@Service
public class ClientService {
    private static final Logger log = LoggerFactory.getLogger(ClientService.class.getName());

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final OrderMapper orderMapper;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper, OrderMapper orderMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.orderMapper = orderMapper;
    }

    public Mono<ClientFullDto> getClient(String clientID) {
        return clientRepository.getClient(clientID).map(clientMapper::clientToFullDto).switchIfEmpty(Mono.defer(() -> {
            throw new RuntimeException(String.format("Client with id %s not found", clientID));
        }));
    }

    public Mono<ClientFullDto> createClient(ClientLightDto clientLightDto) {
        return Mono.just(clientMapper.lightToEntity(clientLightDto))
                .switchIfEmpty(Mono.defer(() -> Mono.error(() -> new RuntimeException("Error during mapping dto to entity"))))
                .flatMap(clientRepository::createClient)
                .map(clientMapper::clientToFullDto);
    }

    public Flux<ClientFullDto> getAllClients() {
        return this.clientRepository.allClients().map(clientMapper::clientToFullDto);
    }

    public Mono<Boolean> deleteClientById(String clientId) {
        return this.clientRepository.getClient(clientId) // check if client exists
                .flatMap(client -> this.clientRepository.deleteClient(clientId) // delete it if exists
                        .thenReturn(true)) // return true after deletion
                .switchIfEmpty(Mono.just(false)) // return false if the client not exists
                .onErrorResume(throwable -> {
                    log.error("Error while deleting client with id {}", clientId, throwable);
                    return Mono.just(false);
                });
    }

    public Flux<String> deleteClientsById(String[] clientIds) {
        return Flux.fromIterable(List.of(clientIds))
                .flatMap(s -> deleteClientById(s)
                        .filter(deleted -> deleted)
                        .map(aBoolean -> s))
                .delayElements(Duration.ofMillis(200));
    }

    public Flux<ClientOrdersDto> getClientsOrders() {
        return this.clientRepository.getClientsOrdersInfos()
                .map(tuple ->
                        ClientOrdersDto.builder()
                                .client(clientMapper.clientToLightDto(tuple.getT1()))
                                .orders(orderMapper.toListFullDto(tuple.getT2())).build());
    }
}
