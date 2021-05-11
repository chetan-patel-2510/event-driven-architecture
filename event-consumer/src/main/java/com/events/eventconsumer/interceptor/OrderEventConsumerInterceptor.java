package com.events.eventconsumer.interceptor;

import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.Map;

public class OrderEventConsumerInterceptor implements ConsumerInterceptor {
    @Override
    public ConsumerRecords onConsume(ConsumerRecords consumerRecords) {
        System.out.println("Consumer Records size: " + consumerRecords.count());
        return consumerRecords;
    }

    @Override
    public void close() {

    }

    @Override
    public void onCommit(Map map) {
        System.out.println("on Commit: " + map);
    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
