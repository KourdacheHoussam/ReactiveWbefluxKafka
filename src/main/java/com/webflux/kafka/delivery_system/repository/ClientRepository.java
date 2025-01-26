package com.webflux.kafka.delivery_system.repository;

import com.webflux.kafka.delivery_system.entity.Client;
import com.webflux.kafka.delivery_system.entity.Order;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.List;

/**
 * @author : Houssam KOURDACHE
 */
public interface ClientRepository {
    Mono<Client> createClient(Client client);

    Mono<Client> getClient(String id);

    Flux<Client> allClients();

    Mono<Void> deleteClient(String clientId);

    Flux<String> deleteClients(List<String> clientsListId);

    Flux<Tuple2<Client, List<Order>>> getClientsOrdersInfos();
}
