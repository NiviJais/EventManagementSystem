package com.backendAssignment.GyanGrove.Controller;


import com.backendAssignment.GyanGrove.Service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public ResponseEntity<String> getWeather(
            @RequestParam("city") String city,
            @RequestParam("date") String date) {
        String weather = weatherService.getWeather(city, date);
        return ResponseEntity.ok(weather);
    }
}

