package com.cgv.service;

import com.cgv.domain.dto.TicketDto;
import com.cgv.domain.dto.TicketInfoDto;

import java.util.List;

public interface TicketService {
    List<TicketInfoDto> findByUsername(String username);

    void saveTicket(TicketDto ticketDto, String username);
}
