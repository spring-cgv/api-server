package com.cgv.domain.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class UserDto {
    @NotBlank
    private String username;

    @NotBlank
    private String pwd;
}