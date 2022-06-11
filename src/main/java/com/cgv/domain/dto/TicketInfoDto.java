package com.cgv.domain.dto;

import com.cgv.domain.entity.Ticket;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TicketInfoDto {
    private Long id;
    private LocalDateTime startDateTime;
    private Integer movieRunningTime;
    private String movieTitle;
    private String screenName;
    private List<String> seats;

    public TicketInfoDto(Ticket ticket) {
        this.id = ticket.getId();
        this.startDateTime = ticket.getSchedule().getStartDateTime();
        this.movieRunningTime = ticket.getSchedule().getMovie().getRunningTime();
        this.movieTitle = ticket.getSchedule().getMovie().getTitle();
        this.screenName = ticket.getSchedule().getScreen().getName();
        this.seats = ticket.getTicketSeats()
                .stream()
                .map(ticketSeat -> ticketSeat.getSeat())
                .map(seat -> "" + seat.getColumn() + seat.getNumber())
                .collect(Collectors.toList());
    }
}
