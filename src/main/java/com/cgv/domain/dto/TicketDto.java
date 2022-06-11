package com.cgv.domain.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class TicketDto {
    @NotNull
    private Long scheduleId;

    @NotNull
    private List<Long> seatIds;
}
