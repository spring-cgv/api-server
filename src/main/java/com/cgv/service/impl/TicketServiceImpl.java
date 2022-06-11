package com.cgv.service.impl;

import com.cgv.domain.dto.TicketDto;
import com.cgv.domain.dto.TicketInfoDto;
import com.cgv.domain.entity.Schedule;
import com.cgv.domain.entity.Seat;
import com.cgv.domain.entity.Ticket;
import com.cgv.domain.entity.TicketSeat;
import com.cgv.repository.ScheduleRepository;
import com.cgv.repository.SeatRepository;
import com.cgv.repository.TicketRepository;
import com.cgv.repository.UserRepository;
import com.cgv.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final ScheduleRepository scheduleRepository;
    private final SeatRepository seatRepository;
    private final UserRepository userRepository;

    @Override
    public List<TicketInfoDto> findByUsername(String username) {
        return ticketRepository.findByUserUsername(username)
                .stream()
                .map(TicketInfoDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public void saveTicket(TicketDto ticketDto, String username) {
        Schedule schedule = scheduleRepository.findById(ticketDto.getScheduleId()).get();

        List<Seat> seats = ticketDto.getSeatIds()
                .stream()
                .map(seatId -> {
                    Seat seat = seatRepository.findById(seatId).get();
                    if (!seat.getScreen().equals(schedule.getScreen()))
                        throw new IllegalArgumentException();
                    return seat;
                })
                .collect(Collectors.toList());
        
        // TODO: 이미 선택한 좌석 예외

        Ticket ticket = Ticket.builder()
                .user(userRepository.findByUsername(username).get())
                .schedule(schedule)
                .totalPrice(10000)
                .build();

        List<TicketSeat> ticketSeats = seats.stream()
                .map(seat -> TicketSeat.builder()
                        .ticket(ticket)
                        .seat(seat)
                        .build())
                .collect(Collectors.toList());
        ticket.setTicketSeats(ticketSeats);

        ticketRepository.save(ticket);
    }
}
