package com.cgv.domain.dto;

import com.cgv.domain.Rating;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MovieDto {
    private String title;
    private String synopsis;
    private String posterUrl;
    private LocalDate openingDate;
    private Integer runningTime;
    private Rating rating;
    private String director;
    private String actor;
    private String genre;
    private Double reviewStarAvg;
    private Double ticketRatio;
}
