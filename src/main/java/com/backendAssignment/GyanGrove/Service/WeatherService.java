package com.backendAssignment.GyanGrove.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Value("${weather.api.url}")
    private String weatherApiUrl;

    @Autowired
    private RestTemplate restTemplate;

    public String getWeather(String city, String date) {
        String apiUrl = weatherApiUrl + "?city=" + city + "&date=" + date;
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

        HttpStatus statusCode = (HttpStatus) response.getStatusCode();

        if (statusCode.is2xxSuccessful()) {
            return response.getBody();
        } else if (statusCode.is4xxClientError()) {
            // Handle client errors (e.g., 404 Not Found)
            return "API client error: " + statusCode;
        } else if (statusCode.is5xxServerError()) {
            // Handle server errors (e.g., 500 Internal Server Error)
            return "API server error: " + statusCode;
        } else {
            // Handle other status codes
            return "Unexpected API response: " + statusCode;
        }
    }

}
