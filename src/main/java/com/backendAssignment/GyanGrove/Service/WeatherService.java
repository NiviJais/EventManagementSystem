package com.backendAssignment.GyanGrove.Service;

import com.backendAssignment.GyanGrove.Entity.EventDTO;
import com.backendAssignment.GyanGrove.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class WeatherService {

    @Autowired
    private EventRepository eventRepository;


    // logic for handling external api to get the value of weather
    public String getWeather(String cityName, String date) {

        String apiUrl = "https://gg-backend-assignment.azurewebsites.net/api/Weather?code=KfQnTWHJbg1giyB_Q9Ih3Xu3L9QOBDTuU5zwqVikZepCAzFut3rqsg=="
                + "&city=" + cityName
                + "&date=" + date;

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(apiUrl, String.class);

    }

    // logic for getting city name for database
    public String getCityNameByDate(String date) {

        List<EventDTO> eventDataList = eventRepository.findByDate(date);

        if (eventDataList.isEmpty()) {
            return "City not found for the given date.";
        } else {
            String cityName = eventDataList.get(0).getCity_Name();
            return cityName;
        }
    }

    // logic for getting event name for database
    public String getEventName(String date) {
        List<EventDTO> eventDataList = eventRepository.findByDate(date);
        if (eventDataList.isEmpty()) {
            return "Event Name is not found for the given date.";
        } else {
            String EventName = eventDataList.get(0).getEvent_Name();
            return EventName;
        }
    }

}
