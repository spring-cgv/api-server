package com.cgv.service;

import com.cgv.domain.dto.TicketDto;

import java.util.List;

public interface TicketService {
    List<TicketDto> findByUsername(String username);
}
