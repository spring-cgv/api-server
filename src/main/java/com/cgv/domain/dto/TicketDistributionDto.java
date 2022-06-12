package com.cgv.domain.dto;

import com.cgv.domain.Gender;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class TicketDistributionDto {
    Integer totalCount;
    Map<Gender, Long> genderCount;
    List<Map<String, Object>> ageGroupCount;

    public TicketDistributionDto(Integer totalCount,
                                 Map<Gender, Long> genderCount,
                                 List<Map<String, Object>> ageGroupCount) {

        this.totalCount = totalCount;
        this.genderCount = genderCount;
        this.ageGroupCount = ageGroupCount;
    }
}
