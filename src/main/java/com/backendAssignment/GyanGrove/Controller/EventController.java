package com.backendAssignment.GyanGrove.Controller;

import com.backendAssignment.GyanGrove.Entity.Event;
import com.backendAssignment.GyanGrove.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping("/create")
    public ResponseEntity<String> createEvent(@RequestBody Event event) {
        eventService.createEvent(event);
        return new ResponseEntity<>("Event created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/find")
    public ResponseEntity<List<Event>> findEvents(
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude,
            @RequestParam("date") String date) {
        List<Event> events = eventService.findEvents(latitude, longitude, date);
        return ResponseEntity.ok(events);
    }
}
