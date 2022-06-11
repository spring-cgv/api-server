package com.cgv.domain.dto;

import lombok.Getter;

@Getter
public class SeatDto {
    private Long id;
    private Character column;
    private Integer number;
    private Boolean isAvailable;
}
