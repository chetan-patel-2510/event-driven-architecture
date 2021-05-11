package com.events.eventconsumer.model;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    private String name;

    private int quantity;

    private BigDecimal price;
}
