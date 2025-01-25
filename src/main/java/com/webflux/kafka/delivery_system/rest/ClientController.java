package com.webflux.kafka.delivery_system.rest;

import com.webflux.kafka.delivery_system.application.dto.ClientFullDto;
import com.webflux.kafka.delivery_system.entity.Order;
import com.webflux.kafka.delivery_system.infrastructure.repository.dao.ClientDAO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Houssam KOURDACHE
 */
@RestController
@RequestMapping("/clients")
public class ClientController {

    public ClientController() {
    }
    @GetMapping("/{clientId}")
    public ClientFullDto getClient(@PathVariable("clientId") String clientId) {
        return null;
    }
}
