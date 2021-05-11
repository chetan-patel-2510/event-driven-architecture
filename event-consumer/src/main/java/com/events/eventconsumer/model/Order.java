package com.events.eventconsumer.model;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String orderNumber;

    private String orderDate;

    private String customerName;

    private List<OrderItem> orderItems;
}
