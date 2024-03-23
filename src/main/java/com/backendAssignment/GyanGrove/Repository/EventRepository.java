package com.backendAssignment.GyanGrove.Repository;

import com.backendAssignment.GyanGrove.Entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findByDateAndLatitudeAndLongitude(String date, double latitude, double longitude);
}
