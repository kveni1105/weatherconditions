package com.kce.wether.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kce.wether.entity.Weather;

import java.time.LocalDateTime;
import java.util.List;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

  
    List<Weather> findByDatetimeUtcBetween(LocalDateTime start,
                                           LocalDateTime end);

    @Query("SELECT w FROM Weather w WHERE MONTH(w.datetimeUtc) = :month")
    List<Weather> findByMonth(@Param("month") int month);

    @Query("SELECT w FROM Weather w WHERE YEAR(w.datetimeUtc) = :year")
    List<Weather> findByYear(@Param("year") int year);
    
    boolean existsByDatetimeUtc(LocalDateTime datetimeUtc);
}