package com.cgv.web.controller;

import com.cgv.domain.dto.MovieDto;
import com.cgv.domain.dto.TicketDistributionDto;
import com.cgv.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/{id}")
    public ResponseEntity getMovie(@PathVariable("id") Long movieId) {
        try {
            MovieDto movieDto = movieService.findById(movieId);
            return new ResponseEntity(movieDto, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/tickets/distribution")
    public ResponseEntity getTicketDistribution(@PathVariable("id") Long movieId) {
        try {
            TicketDistributionDto ticketDistributionDto = movieService.getTicketDistribution(movieId);
            return new ResponseEntity(ticketDistributionDto, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
