package com.cgv.web.controller;

import com.cgv.domain.CustomUser;
import com.cgv.domain.dto.TicketDto;
import com.cgv.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("")
    public List<TicketDto> getTicketByUsername(@AuthenticationPrincipal CustomUser customUser) {
        return ticketService.findByUsername(customUser.getUsername());
    }
}
