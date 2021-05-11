package com.events.eventproducer.controller;

import com.events.eventproducer.publisher.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/events/orders/{eventCount}")
    public ResponseEntity<String> createOrderEvent(@PathVariable("eventCount") int eventCount) {
        eventService.publishEvent(eventCount);
        return ResponseEntity.ok("Order Events published successfully.");
    }
}
