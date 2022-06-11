package com.cgv.web.controller;

import com.cgv.domain.CustomUser;
import com.cgv.domain.dto.TicketDto;
import com.cgv.domain.dto.TicketInfoDto;
import com.cgv.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("")
    public ResponseEntity ticketMovie(@RequestBody @Validated TicketDto ticketDto,
                                      @AuthenticationPrincipal CustomUser customUser) {
        try {
            ticketService.saveTicket(ticketDto, customUser.getUsername());
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("")
    public List<TicketInfoDto> getTicketByUsername(@AuthenticationPrincipal CustomUser customUser) {
        return ticketService.findByUsername(customUser.getUsername());
    }
}
