package com.cgv.web.controller;

import com.cgv.domain.dto.DiscountPolicyDto;
import com.cgv.domain.dto.ScheduleDto;
import com.cgv.domain.dto.SeatDto;
import com.cgv.service.DiscountPolicyService;
import com.cgv.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final DiscountPolicyService discountPolicyService;

    @GetMapping("")
    public List<Map<String, Object>> getSchedules(
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate screenDate) {

        if (screenDate == null) {
            screenDate = LocalDate.now().plusDays(1);
        }

        return scheduleService.findSchedulesOnDate(screenDate);
    }

    @GetMapping("/{id}/discount-policies")
    public ResponseEntity getDiscountPolicyFromSchedule(@PathVariable("id") Long scheduleId) {
        try {
            DiscountPolicyDto discountPolicyDto = discountPolicyService.findByScheduleId(scheduleId);
            return new ResponseEntity(discountPolicyDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping("/{id}/discount-policies")
    public ResponseEntity editDiscountPolicy(@PathVariable("id") Long scheduleId,
                                             @RequestBody @Validated DiscountPolicyDto discountPolicyDto) {

        try {
            discountPolicyService.editDiscountPolicy(scheduleId, discountPolicyDto);
            return new ResponseEntity(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getSchedule(@PathVariable Long id) {
        try {
            ScheduleDto scheduleDto = scheduleService.getScheduleById(id);
            return new ResponseEntity(scheduleDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/seats")
    public ResponseEntity getSeatInfos(@PathVariable("id") Long scheduleId) {
        try {
            List<SeatDto> seatDtos = scheduleService.getSeatInfosByScheduleId(scheduleId);
            return new ResponseEntity(seatDtos, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
