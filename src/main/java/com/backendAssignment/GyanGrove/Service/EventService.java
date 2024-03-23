package com.backendAssignment.GyanGrove.Service;

import com.backendAssignment.GyanGrove.Entity.Event;
import com.backendAssignment.GyanGrove.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public void createEvent(Event event) {
        eventRepository.save(event);
    }

    public List<Event> findEvents(double latitude, double longitude, String date) {
        // Implement logic to find events based on provided latitude, longitude, and date
        // This logic depends on your specific requirements
        // For simplicity, I'm returning all events in this method
        return eventRepository.findAll();
    }
}
