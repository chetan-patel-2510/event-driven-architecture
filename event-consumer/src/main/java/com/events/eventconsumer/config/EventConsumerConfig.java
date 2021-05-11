package com.events.eventconsumer.config;

import com.events.eventconsumer.model.OrderEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Configuration
public class EventConsumerConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper;
    }

    @Bean
    public Consumer<Message<OrderEvent>> orderEventConsumer() {
        return orderEventMessage -> {
            System.out.println("Event received in order event queue: " + orderEventMessage.getPayload());
            Acknowledgment acknowledgment = orderEventMessage.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
            System.out.println(acknowledgment);
            OrderEvent orderEvent = orderEventMessage.getPayload();
            try {
                System.out.println(objectMapper().writeValueAsString(orderEvent));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            if (orderEvent.getEventId() == 3) {
                throw new RuntimeException("Failed to process order");
            }
            acknowledgment.acknowledge();
        };
    }

    @Bean
    public Consumer<Message<OrderEvent>> dlqConsumer() {
        return orderEventMessage -> {
            System.out.println("Events received in dlq : " + orderEventMessage.getPayload());
            OrderEvent orderEvent = orderEventMessage.getPayload();
            try {
                System.out.println(objectMapper().writeValueAsString(orderEvent));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        };
    }

}
