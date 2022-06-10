package com.cgv.domain.dto;

import com.cgv.domain.Rating;
import com.cgv.domain.entity.Movie;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
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

    public MovieDto(Movie movie) {
        this.title = movie.getTitle();
        this.synopsis = movie.getSynopsis();
        this.posterUrl = movie.getPosterUrl();
        this.openingDate = movie.getOpeningDate();
        this.runningTime = movie.getRunningTime();
        this.rating = movie.getRating();
        this.director = movie.getDirector();
        this.actor = movie.getActor();
        this.genre = movie.getGenre();
    }
}
