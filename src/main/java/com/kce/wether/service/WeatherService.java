package com.kce.wether.service;

import com.kce.wether.dto.MonthlyStatusDTO;
import com.kce.wether.dto.WeatherResponseDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface WeatherService {

    List<WeatherResponseDTO> getByDate(LocalDate date);

    List<WeatherResponseDTO> getByMonth(int month);

    Map<Integer, MonthlyStatusDTO> getYearlyStats(int year);
    void loadFromFile(MultipartFile file) throws Exception;
}