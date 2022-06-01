package com.cgv.repository;

import com.cgv.domain.entity.Ticket;
import com.cgv.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT t.user FROM Ticket t JOIN t.schedule s WHERE s.movie.id = :movieId")
    List<User> findTicketedUsersByMovieId(Long movieId);
}
