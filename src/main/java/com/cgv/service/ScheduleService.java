package com.cgv.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ScheduleService {
    List<Map<String, Object>> findSchedulesOnDate(LocalDate screenDate);

    List<Map<String, Object>> findSchedulesByMovieIdOnDate(Long movieId, LocalDate screenDate);
}
