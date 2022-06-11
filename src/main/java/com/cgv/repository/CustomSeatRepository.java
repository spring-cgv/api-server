package com.cgv.repository;

import com.cgv.domain.dto.SeatDto;

import java.util.List;

public interface CustomSeatRepository {
    List<SeatDto> findDtosByScreenId(Long screenId);
}
