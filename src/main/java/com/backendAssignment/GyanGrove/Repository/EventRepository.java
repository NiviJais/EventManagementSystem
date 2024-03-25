package com.backendAssignment.GyanGrove.Repository;

import com.backendAssignment.GyanGrove.Entity.EventDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<EventDTO, Integer> {
    List<EventDTO> findByDate(String date);
}
