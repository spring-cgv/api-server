package com.cgv.domain.dto;

import com.cgv.domain.Gender;
import lombok.Getter;

import java.util.Map;

@Getter
public class TicketDistributionDto {
    Integer totalCount;
    Map<Gender, Long> genderCount;
    Map<Integer, Long> ageGroupCount;

    public TicketDistributionDto(Integer totalCount,
                                 Map<Gender, Long> genderCount,
                                 Map<Integer, Long> ageGroupCount) {

        this.totalCount = totalCount;
        this.genderCount = genderCount;
        this.ageGroupCount = ageGroupCount;

        for (int i = 10; i <= 50; i += 10) {
            this.ageGroupCount.putIfAbsent(i, 0L);
        }
    }
}
