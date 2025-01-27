package com.webflux.kafka.delivery_system.kafka.processor;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

/**
 * @author : Houssam KOURDACHE
 * Is a middle layer who is going to receive data from a producer, transform that content, and forward it to the consumers
 * based on a named topic
 */
@Configuration
public class KafkaProcessors {

    @PostConstruct
    public void after() {
        System.out.println("---------------> Processors started <---------------");
    }

    @Bean
    public Function<String, String> processorBinding() {
        return s -> "Hello  :  " + s.toUpperCase();
    }

    @Bean
    public Function<Integer, Integer> multiplyNumber() {
        System.out.println("---------------> multiply number started <---------------");
        return intIn -> intIn * intIn;
    }
}
