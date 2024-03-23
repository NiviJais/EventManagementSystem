package com.backendAssignment.GyanGrove.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.Time;

import com.backendAssignment.GyanGrove.Entity.Event;
import com.backendAssignment.GyanGrove.Repository.EventRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CsvDataService {

    @Autowired
    private EventRepository eventRepository;

    public void saveCsvData(String filePath) {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));
             CSVReader csvReader = new CSVReader(reader)) {
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                // Assuming CSV structure: event_name,city_name,date,time,latitude,longitude
                Event event = new Event();
                event.setEventName(nextRecord[0]);
                event.setCityName(nextRecord[1]);
                event.setDate(Date.valueOf(nextRecord[2]));
                event.setTime(Time.valueOf(nextRecord[3]));
                event.setLatitude(Double.parseDouble(nextRecord[4]));
                event.setLongitude(Double.parseDouble(nextRecord[5]));

                eventRepository.save(event);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
            // Handle exception
        }
    }
}
