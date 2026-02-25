package com.kce.wether.service.impl;

import com.kce.wether.dto.MonthlyStatusDTO;
import com.kce.wether.dto.WeatherResponseDTO;
import com.kce.wether.entity.Weather;
import com.kce.wether.repository.WeatherRepository;
import com.kce.wether.service.WeatherService;
import com.opencsv.CSVReader;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository repository;

    public WeatherServiceImpl(WeatherRepository repository) {
        this.repository = repository;
    }

    // 1️⃣ Get Weather By Date
    @Override
    public List<WeatherResponseDTO> getByDate(LocalDate date) {

        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(23, 59);

        return repository.findByDatetimeUtcBetween(start, end)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 2️⃣ Get Weather By Month
    @Override
    public List<WeatherResponseDTO> getByMonth(int month) {

        return repository.findByMonth(month)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 3️⃣ Yearly Stats
    @Override
    public Map<Integer, MonthlyStatusDTO> getYearlyStats(int year) {

        List<Weather> yearlyData = repository.findByYear(year);

        Map<Integer, List<Double>> monthTemps = new HashMap<>();

        for (Weather w : yearlyData) {

            if (w.getTempm() == null) continue;

            int month = w.getDatetimeUtc().getMonthValue();

            monthTemps
                    .computeIfAbsent(month, k -> new ArrayList<>())
                    .add(w.getTempm());
        }

        Map<Integer, MonthlyStatusDTO> result = new HashMap<>();

        for (Integer month : monthTemps.keySet()) {

            List<Double> temps = monthTemps.get(month);
            Collections.sort(temps);

            double min = temps.get(0);
            double max = temps.get(temps.size() - 1);

            double median;
            int size = temps.size();

            if (size % 2 == 0) {
                median = (temps.get(size / 2 - 1) + temps.get(size / 2)) / 2;
            } else {
                median = temps.get(size / 2);
            }

            result.put(month, new MonthlyStatusDTO(min, max, median));
        }

        return result;
    }

    // 🔥 CSV Upload via API
    @Override
    public void loadFromFile(MultipartFile file) throws Exception {

        CSVReader reader =
                new CSVReader(
                        new InputStreamReader(file.getInputStream()));

        reader.readNext(); // skip header

        String[] line;

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyyMMdd-HH:mm");

        List<Weather> batchList = new ArrayList<>();

        while ((line = reader.readNext()) != null) {

            try {

                LocalDateTime dateTime =
                        LocalDateTime.parse(line[0], formatter);

                if (repository.existsByDatetimeUtc(dateTime)) {
                    continue;
                }

                Weather weather = new Weather();

                weather.setDatetimeUtc(dateTime);
                weather.setConds(line[1]);
                weather.setTempm(parseDouble(line[11]));
                weather.setHum(parseDouble(line[6]));
                weather.setPressurem(parseDouble(line[8]));

                batchList.add(weather);

                if (batchList.size() == 1000) {
                    repository.saveAll(batchList);
                    batchList.clear();
                }

            } catch (Exception e) {
                System.out.println("Skipping row due to error");
            }
        }

        if (!batchList.isEmpty()) {
            repository.saveAll(batchList);
        }

        reader.close();
    }

    private WeatherResponseDTO convertToDTO(Weather weather) {

        return new WeatherResponseDTO(
                weather.getConds(),
                weather.getTempm(),
                weather.getHum(),
                weather.getPressurem()
        );
    }

    private Double parseDouble(String value) {
        if (value == null || value.isEmpty() || value.equals("-9999")) {
            return null;
        }
        return Double.parseDouble(value);
    }
}