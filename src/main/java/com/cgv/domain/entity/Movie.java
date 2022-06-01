package com.cgv.domain.entity;

import com.cgv.domain.Rating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @Column(name = "movie_id")
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String synopsis;

    @Column(nullable = false)
    private String posterUrl;

    @Column(nullable = false)
    private LocalDate openingDate;

    @Column(nullable = false)
    private Integer runningTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rating rating;

    private String director;

    private String actor;

    private String genre;
}
