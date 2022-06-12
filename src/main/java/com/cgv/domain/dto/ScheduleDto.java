package com.cgv.domain.dto;

import com.cgv.domain.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleDto {
    private String screenName;
    private LocalDateTime startDateTime;
    private Integer movieRunningTime;

    public ScheduleDto(Schedule schedule) {
        this.screenName = schedule.getScreen().getName();
        this.startDateTime = schedule.getStartDateTime();
        this.movieRunningTime = schedule.getMovie().getRunningTime();
    }
}
