package com.backendAssignment.GyanGrove.Service;

import com.backendAssignment.GyanGrove.Entity.EventDTO;
import com.backendAssignment.GyanGrove.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private DistanceService distanceCalculatorService;

    @Autowired
    private WeatherService weatherService;

    // logic for adding data to DB
    public EventDTO saveEventDTO(EventDTO eventDTO) {
        return eventRepository.save(eventDTO);
    }
}
