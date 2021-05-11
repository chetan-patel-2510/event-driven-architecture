package com.events.eventproducer.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class Order {
    private String orderNumber;

    private String orderDate;

    private String customerName;

    private List<OrderItem> orderItems;
}
