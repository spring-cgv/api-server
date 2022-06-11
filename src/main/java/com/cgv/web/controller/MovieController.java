package com.cgv.web.controller;

import com.cgv.domain.dto.MovieDto;
import com.cgv.domain.dto.TicketDistributionDto;
import com.cgv.service.MovieService;
import com.cgv.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    private final ScheduleService scheduleService;

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

    @GetMapping("/{id}/schedules")
    public ResponseEntity getSchedules(
            @PathVariable("id") Long movieId,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate screenDate) {

        if (screenDate == null) {
            screenDate = LocalDate.now().plusDays(1);
        }

        try {
            List<Map<String, Object>> scheduleInfos = scheduleService.findSchedulesByMovieIdOnDate(movieId, screenDate);
            return new ResponseEntity(scheduleInfos, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
