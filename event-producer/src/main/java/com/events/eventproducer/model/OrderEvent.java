package com.events.eventproducer.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class OrderEvent {

    private int eventId;

    private String eventName;

    private String eventDate;

    private Order order;
}
