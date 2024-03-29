package com.cgv.repository;

import com.cgv.domain.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("SELECT s FROM Schedule s WHERE FORMATDATETIME(s.startDateTime, 'yyyy-MM-dd') = :screenDate")
    List<Schedule> findByScreenDate(@Param("screenDate") LocalDate screenDate);

    @Query("SELECT s FROM Schedule s WHERE s.movie.id = :movieId AND FORMATDATETIME(s.startDateTime, 'yyyy-MM-dd') = :screenDate")
    List<Schedule> findByMovieIdAndScreenDate(@Param("movieId") Long movieId, @Param("screenDate") LocalDate screenDate);
}
