package com.backendAssignment.GyanGrove.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

// this is for the details of pagination in postman
public class PaginationResponse<T> {
    private List<T> events;
    private int page;
    private int pageSize;
    private int totalEvents;
    private int totalPages;

}