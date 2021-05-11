package com.events.eventproducer.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class OrderItem {

    private String name;

    private int quantity;

    private BigDecimal price;
}
