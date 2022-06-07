package com.cgv.domain.dto;

import com.cgv.domain.CustomUser;
import com.cgv.domain.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class UserDto {
    @NotBlank
    private String username;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String pwd;

    @NotBlank
    private String name;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDate birth;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Gender gender;

    public UserDto(CustomUser customUser) {
        this.username = customUser.getUsername();
        this.name = customUser.getName();
    }
}