package com.cgv.web.controller;

import com.cgv.domain.dto.MovieDto;
import com.cgv.domain.dto.TicketDistributionDto;
import com.cgv.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping("")
    public List<MovieDto> getMoviesOnCondition(@RequestParam(required = false) String titleKey,
                                               @RequestParam(required = false) String actorKey,
                                               Pageable pageable) {

        return movieService.findMoviesOnCondition(titleKey, actorKey, pageable);
    }

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
