package com.webflux.kafka.delivery_system.infrastructure.repository.dao;

import com.webflux.kafka.delivery_system.entity.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Houssam KOURDACHE
 */
@Repository
public interface ClientDAO extends ReactiveMongoRepository<Client, String> {

}
