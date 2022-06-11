package com.cgv.service.impl;

import com.cgv.domain.dto.SeatDto;
import com.cgv.domain.entity.Movie;
import com.cgv.domain.entity.Schedule;
import com.cgv.domain.entity.Screen;
import com.cgv.repository.ScheduleRepository;
import com.cgv.repository.SeatRepository;
import com.cgv.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@RequiredArgsConstructor
@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final SeatRepository seatRepository;

    @Override
    public List<Map<String, Object>> findSchedulesOnDate(LocalDate screenDate) {
        List<Schedule> schedules = scheduleRepository.findByScreenDate(screenDate);
        return createListFromSchedules(schedules);
    }

    @Override
    public List<Map<String, Object>> findSchedulesByMovieIdOnDate(Long movieId, LocalDate screenDate) {
        List<Schedule> schedules = scheduleRepository.findByMovieIdAndScreenDate(movieId, screenDate);
        return createListFromSchedules(schedules);
    }

    @Override
    public List<SeatDto> getSeatInfosByScheduleId(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).get();
        return seatRepository.findDtosBySchedule(scheduleId, schedule.getScreen().getId());
    }

    public List<Map<String, Object>> createListFromSchedules(List<Schedule> schedules) {
        List<Map<String, Object>> scheduleInfos = new ArrayList();
        schedules.stream()
                .forEach(schedule -> addSchedule(scheduleInfos, schedule));
        return scheduleInfos;
    }

    private void addSchedule(List<Map<String, Object>> movies, Schedule schedule) {
        Map<String, Object> foundMovieScheduleMap = findMap(movies, "movieId", schedule.getMovie().getId());

        // 영화 X
        if (foundMovieScheduleMap == null) {
            List<Map<String, Object>> schedules = new ArrayList();
            schedules.add(getScheduleMap(schedule));

            List<Map<String, Object>> screens = new ArrayList();
            screens.add(getScreenScheduleMap(schedule.getScreen(), schedules));

            movies.add(getMovieScheduleMap(schedule.getMovie(), screens));
        } else {
            List<Map<String, Object>> screens = (List<Map<String, Object>>) foundMovieScheduleMap.get("screens");
            Map<String, Object> foundScreenScheduleMap = findMap(screens, "screenId", schedule.getScreen().getId());

            // 영화 O, 상영관 X
            if (foundScreenScheduleMap == null) {
                List<Map<String, Object>> schedules = new ArrayList();
                schedules.add(getScheduleMap(schedule));

                screens.add(getScreenScheduleMap(schedule.getScreen(), schedules));
            } else {    // 영화-상영관 O
                List<Map<String, Object>> schedules = (List<Map<String, Object>>) foundScreenScheduleMap.get("schedules");
                schedules.add(getScheduleMap(schedule));
            }
        }
    }

    private Map<String, Object> findMap(Collection<Map<String, Object>> collection, String key, Object value) {
        return collection.stream()
                .filter(map -> map.get(key).equals(value))
                .findFirst()
                .orElse(null);
    }

    private Map<String, Object> getScheduleMap(Schedule schedule) {
        int totalSeatCnt = schedule.getScreen().getSeats().size();
        long unavailableSeatCnt = schedule.getTickets()
                .stream()
                .flatMap(ticket -> ticket.getTicketSeats().stream())
                .count();

        Map<String, Object> scheduleMap = new HashMap();
        scheduleMap.put("scheduleId", schedule.getId());
        scheduleMap.put("scheduleStartDateTime", schedule.getStartDateTime());
        scheduleMap.put("availableSeatCnt", totalSeatCnt - unavailableSeatCnt);
        return scheduleMap;
    }

    private Map<String, Object> getScreenScheduleMap(Screen screen, List<Map<String, Object>> schedules) {
        Map<String, Object> screenScheduleMap = new HashMap();
        screenScheduleMap.put("screenId", screen.getId());
        screenScheduleMap.put("screenName", screen.getName());
        screenScheduleMap.put("totalSeatCnt", screen.getSeats().size());
        screenScheduleMap.put("schedules", schedules);
        return screenScheduleMap;
    }

    private Map<String, Object> getMovieScheduleMap(Movie movie, List<Map<String, Object>> screens) {
        Map<String, Object> movieScheduleMap = new HashMap();
        movieScheduleMap.put("movieId", movie.getId());
        movieScheduleMap.put("movieTitle", movie.getTitle());
        movieScheduleMap.put("movieRating", movie.getRating());
        movieScheduleMap.put("movieGenre", movie.getGenre());
        movieScheduleMap.put("movieRunningTime", movie.getRunningTime());
        movieScheduleMap.put("movieOpeningDate", movie.getOpeningDate());
        movieScheduleMap.put("screens", screens);
        return movieScheduleMap;
    }
}
