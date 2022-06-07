package com.cgv.web.controller;

import com.cgv.domain.CustomUser;
import com.cgv.domain.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/session")
public class SessionController {
    @GetMapping("")
    public UserDto getSessionInfo(@AuthenticationPrincipal CustomUser customUser) {
        return new UserDto(customUser);
    }
}
