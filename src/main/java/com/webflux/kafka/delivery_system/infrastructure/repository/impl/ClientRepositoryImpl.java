package com.webflux.kafka.delivery_system.infrastructure.repository.impl;

import com.webflux.kafka.delivery_system.entity.Client;
import com.webflux.kafka.delivery_system.entity.Order;
import com.webflux.kafka.delivery_system.infrastructure.repository.dao.ClientDAO;
import com.webflux.kafka.delivery_system.infrastructure.repository.dao.OrderDAO;
import com.webflux.kafka.delivery_system.repository.ClientRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.List;

/**
 * @author : Houssam KOURDACHE
 */
@Repository
@Log4j2
public class ClientRepositoryImpl implements ClientRepository {

    private final ClientDAO clientDAO;
    private final OrderDAO orderDAO;

    public ClientRepositoryImpl(ClientDAO clientDAO,
                                OrderDAO orderDAO) {
        this.clientDAO = clientDAO;
        this.orderDAO = orderDAO;
    }

    @Override
    public Mono<Client> createClient(Client client) {
        return clientDAO.save(client);
    }

    @Override
    public Mono<Client> getClient(String id) {
        return null;
    }

    @Override
    public Flux<Client> allClient() {
        return clientDAO.findAll();
    }

    @Override
    public Mono<Void> deleteClient(String clientId) {
        return clientDAO.deleteById(clientId);
    }

    @Override
    public Flux<String> deleteClients(List<String> clientsListId) {
        int BUFFER_SIZE = 10;
        return Flux.fromIterable(clientsListId)
                .buffer(BUFFER_SIZE) // split the deletion to smaller list of elements to delete
                .flatMap(listIdClientToDelete ->
                    clientDAO.deleteAllById(listIdClientToDelete) // delete a chunk of clients
                            .thenMany(Flux.fromIterable(listIdClientToDelete))
                    // make sure to return deleted elements ids
                    // because deleteAllById() return Mono<Void>, so...
                )
                .delayElements(Duration.ofMillis(500)); //
    }

    private Flux<Order> getClientOrders(String clientId) {
        // the rule of publish() in this method permit to publish toward multiple
        // subscribers, with taking to account the backpressure.
        return orderDAO.findByClientId(clientId).publish().delayElements(Duration.ofMillis(100))
                .doOnError(throwable -> new RuntimeException("Error occurred during publishing event"));
    }

    @Override
    public Mono<Tuple2<Client, List<Order>>> getClientOrdersInfos(String clientId) {
        return Mono.zip(
                getClient(clientId).switchIfEmpty(Mono.error(new RuntimeException("Client not found"))),
                getClientOrders(clientId).collectList() // Return empty list if no orders found
        ).doOnError(e -> new RuntimeException("Error fetching client or orders for clientId: {}", e));

    }
}
