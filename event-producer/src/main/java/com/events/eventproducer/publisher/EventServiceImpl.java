package com.events.eventproducer.publisher;

import com.events.eventproducer.model.Order;
import com.events.eventproducer.model.OrderEvent;
import com.events.eventproducer.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@Service
public class EventServiceImpl implements EventService {

    /**
     * This bean is injected by spring. It is used to publish events to supplier manually.
     */
    @Autowired
    private StreamBridge streamBridge;

    @Override
    public void publishEvent(int count) {
        IntStream.range(0, count).mapToObj(this::createEvent).forEach(orderEvent -> {
            streamBridge.send("producer-out-0", MessageBuilder.withPayload(orderEvent)
                    .setHeader(KafkaHeaders.MESSAGE_KEY, String.valueOf(orderEvent.getEventId())).build());
        });
    }

    public OrderEvent createEvent(int count) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String eventTime = dateTimeFormatter.format(LocalDateTime.now());

        OrderItem orderItem = OrderItem.builder().name("Mobile Phone").price(new BigDecimal("1000")).quantity(1).build();


        Order order = Order.builder().orderDate(eventTime).orderNumber(UUID.randomUUID().toString()).customerName("Chetan").orderItems(Arrays.asList(orderItem)).build();

        OrderEvent orderEvent = OrderEvent.builder().eventId(count).eventDate(eventTime).eventName("Order Created").order(order).build();
        return orderEvent;
    }
}
