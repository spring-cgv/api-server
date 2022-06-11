package com.cgv.repository;

import com.cgv.domain.dto.SeatDto;
import com.cgv.domain.entity.Seat;

import java.util.List;

public interface CustomSeatRepository {
    List<SeatDto> findDtosBySchedule(Long scheduleId, Long screenId);

    boolean checkSeatIsUnAvailable(Long scheduleId, Seat seat);
}
