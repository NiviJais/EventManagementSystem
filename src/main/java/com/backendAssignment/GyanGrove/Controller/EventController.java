package com.backendAssignment.GyanGrove.Controller;

import com.backendAssignment.GyanGrove.Entity.EventDTO;
import com.backendAssignment.GyanGrove.Entity.PaginationResponse;
import com.backendAssignment.GyanGrove.Repository.EventRepository;
import com.backendAssignment.GyanGrove.Service.DistanceService;
import com.backendAssignment.GyanGrove.Service.EventService;
import com.backendAssignment.GyanGrove.Service.WeatherService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    // Injecting DistanceCalculatorService for calculating distances
    @Autowired
    private DistanceService distanceCalculatorService;

    // Injecting WeatherService for retrieving weather data
    @Autowired
    private WeatherService weatherService;

    // Injecting EventDataRepository to perform create or find the details from database
    @Autowired
    private EventRepository eventRepository;

    // Inject EventDataService for handling logic related to event data list (database)
    @Autowired
    private EventService eventDataService;


    @PostMapping("/addEvent")
    public ResponseEntity<EventDTO> addEventToDB(@RequestBody EventDTO eventDataList) {
        // Saving event data list using EventDataService
        EventDTO createdEvents = eventDataService.saveEventDTO(eventDataList);
        // Returning created event data list with status code
        return new ResponseEntity<>(createdEvents, HttpStatus.CREATED);
    }





    // as per the requirement taking three input(latitude,longitude & searchDate ) to perform the operation and return the desire value means list from the database(event list data)
    @GetMapping("/find")
    public PaginationResponse<EventDTO> getEventData(@RequestParam double latitude, @RequestParam double longitude,
                                                          @RequestParam String searchDate, @RequestParam(defaultValue = "1") int page,
                                                          @RequestParam(defaultValue = "10") int pageSize) {
        // logic for searching date starting to end
        List<EventDTO> eventDataList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(searchDate, formatter);

        // logic to Calculate total number of items and starting index for pagination
        int totalItems = 15; // Assuming always fetching data for next 15 days
        int totalEvents = totalItems; // This will replace with the actual total number of events
        int totalPages = (int) Math.ceil((double) totalEvents / pageSize); // basically logic of number of pages
        int startIndex = (page - 1) * pageSize; // staring page

        // for number of days date wise
        for (int i = startIndex; i < totalItems && i < startIndex + pageSize; i++) {
            LocalDate currentDate = startDate.plusDays(i);
            String currentDateString = currentDate.format(formatter);

            // getting city name by date from database
            String cityNameByDate = weatherService.getCityNameByDate(currentDateString);

            // getting event name by date from database
            String eventNameByDate = weatherService.getEventName(currentDateString);

            // getting weather details this is coming from external api that is given in the assignment
            String weatherApiResponse = weatherService.getWeather(cityNameByDate, currentDateString);
            String weather = extractWeatherFromApiResponse(weatherApiResponse);

            // Calculate distance using user's source latitude and longitude this is coming from external api that is given in the assignment
            String distanceApiResponse = distanceCalculatorService.calculateDistance(latitude, longitude);
            Double distance = extractDistanceFromApiResponse(distanceApiResponse);

            // creating event data and returning
            EventDTO eventData = new EventDTO();
            eventData.setEvent_Name(eventNameByDate);
            eventData.setCity_Name(cityNameByDate);
            eventData.setDate(currentDateString);
            eventData.setWeather(weather);
            eventData.setDistance(distance);

            eventDataList.add(eventData);
        }

        return new PaginationResponse<>(eventDataList, page, pageSize, totalEvents, totalPages);
    }

    //logic for extracting data from external distance api basically extracting data value from json
    private Double extractDistanceFromApiResponse(String distanceApiResponse) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(distanceApiResponse);
            String distanceString = rootNode.get("distance").asText();
            return Double.parseDouble(distanceString);

        } catch (IOException | NullPointerException | NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }


    //logic for extracting data from external weather api basically extracting data value from json
    private String extractWeatherFromApiResponse(String weatherApiResponse) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(weatherApiResponse);
            String weather = rootNode.get("weather").asText();
            return weather;
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }


}