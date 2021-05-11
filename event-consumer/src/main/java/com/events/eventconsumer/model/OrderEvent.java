package com.events.eventconsumer.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {

    private int eventId;

    private String eventName;

    private String eventDate;

    private Order order;
}
