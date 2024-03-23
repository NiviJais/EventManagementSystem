package com.backendAssignment.GyanGrove.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DistanceService {

    @Value("${distance.api.url}")
    private String distanceApiUrl;

    @Autowired
    private RestTemplate restTemplate;

    public Double calculateDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        String apiUrl = distanceApiUrl + "?latitude1=" + latitude1 + "&longitude1=" + longitude1 +
                "&latitude2=" + latitude2 + "&longitude2=" + longitude2;
        ResponseEntity<Double> response = restTemplate.getForEntity(apiUrl, Double.class);

        HttpStatus statusCode = (HttpStatus) response.getStatusCode();

        if (statusCode.is2xxSuccessful()) {
            return response.getBody();
        } else if (statusCode.is4xxClientError()) {
            // Handle client errors (e.g., 404 Not Found)
            System.out.println("API client error: " + statusCode);
            return null;
        } else if (statusCode.is5xxServerError()) {
            // Handle server errors (e.g., 500 Internal Server Error)
            System.out.println("API server error: " + statusCode);
            return null;
        } else {
            // Handle other status codes
            System.out.println("Unexpected API response: " + statusCode);
            return null;
        }
    }
}
