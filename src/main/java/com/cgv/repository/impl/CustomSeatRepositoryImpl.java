package com.cgv.repository.impl;

import com.cgv.domain.dto.SeatDto;
import com.cgv.domain.entity.QSeat;
import com.cgv.domain.entity.QTicket;
import com.cgv.domain.entity.QTicketSeat;
import com.cgv.repository.CustomSeatRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class CustomSeatRepositoryImpl implements CustomSeatRepository {

    private final JPAQueryFactory queryFactory;
    private final QSeat qSeat = QSeat.seat;
    private final QTicket qTicket = QTicket.ticket;
    private final QTicketSeat qTicketSeat = QTicketSeat.ticketSeat;

    @Override
    public List<SeatDto> findDtosBySchedule(Long scheduleId, Long screenId) {
        return queryFactory.select(
                Projections.fields(
                        SeatDto.class,
                        qSeat.id,
                        qSeat.column,
                        qSeat.number,
                        qSeat.id.notIn(unAvailableSeats(scheduleId)).as("isAvailable")))
                .from(qSeat)
                .where(qSeat.screen.id.eq(screenId))
                .fetch();
    }

    public JPQLQuery<Long> unAvailableSeats(Long scheduleId) {
        return JPAExpressions.select(qTicketSeat.seat.id)
                .from(qTicketSeat)
                .join(qTicketSeat.ticket, qTicket)
                .where(qTicket.schedule.id.eq(scheduleId));
    }
}
