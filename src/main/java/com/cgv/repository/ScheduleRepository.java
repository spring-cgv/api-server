package com.cgv.repository;

import com.cgv.domain.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("SELECT s FROM Schedule s WHERE FORMATDATETIME(s.startDateTime, 'yyyy-MM-dd') = :screenDate")
    List<Schedule> findByScreenDate(LocalDate screenDate);
}
