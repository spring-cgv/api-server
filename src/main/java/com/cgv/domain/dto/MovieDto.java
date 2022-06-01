package com.cgv.domain.dto;

import com.cgv.domain.Rating;
import com.cgv.domain.entity.Movie;
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
