package com.events.eventproducer.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

public class OrderEventProducerInterceptor implements ProducerInterceptor {
    @Override
    public ProducerRecord onSend(ProducerRecord producerRecord) {
        System.out.println("On Send");
        return producerRecord;
    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        System.out.println("On Acknoledgement");
    }

    @Override
    public void close() {
        System.out.println("On Close");
    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
