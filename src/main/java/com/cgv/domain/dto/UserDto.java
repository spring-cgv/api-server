package com.cgv.domain.dto;

import com.cgv.domain.Gender;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
public class UserDto {
    @NotBlank
    private String username;

    @NotBlank
    private String pwd;

    @NotBlank
    private String name;

    @NotNull
    private LocalDate birth;

    @NotNull
    private Gender gender;
}