package com.cgv.repository;

import com.cgv.domain.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long>, CustomSeatRepository {
}
