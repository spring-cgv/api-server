package com.cgv.service.impl;

import com.cgv.domain.dto.TicketInfoDto;
import com.cgv.repository.TicketRepository;
import com.cgv.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Override
    public List<TicketInfoDto> findByUsername(String username) {
        return ticketRepository.findByUserUsername(username)
                .stream()
                .map(TicketInfoDto::new)
                .collect(Collectors.toList());
    }
}
