package com.kce.wether.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kce.wether.dto.MonthlyStatusDTO;
import com.kce.wether.dto.WeatherResponseDTO;
import com.kce.wether.service.WeatherService;

@RestController
@RequestMapping("/weather")
public class WeatherController {

	private final WeatherService service;
	public  WeatherController(WeatherService service)
	{
		this.service=service;
	}
	
	 @GetMapping("/date/{date}")
	    public ResponseEntity<List<WeatherResponseDTO>> getByDate(
	            @PathVariable String date) {

	        try {
	            LocalDate parsedDate = LocalDate.parse(date);
	            List<WeatherResponseDTO> result = service.getByDate(parsedDate);
	            return ResponseEntity.ok(result);
	        } catch (DateTimeParseException e) {
	            return ResponseEntity.badRequest()
	                    .body(null);
	        }
	    }
	 
	 @GetMapping("/stats/{year}")
	 
	 public ResponseEntity<Map<Integer,MonthlyStatusDTO>> getYearStatus(
			@PathVariable int year )
	 {
		 Map<Integer, MonthlyStatusDTO> result=service.getYearlyStats(year);
		 return ResponseEntity.ok(result);
	 }
	 @PostMapping("/upload")
	 public ResponseEntity<String> uploadFile(
	         @RequestParam("file") MultipartFile file) {

	     try {
	         service.loadFromFile(file);
	         return ResponseEntity.ok("File uploaded successfully");
	     } catch (Exception e) {
	         return ResponseEntity.badRequest()
	                 .body("Upload failed");
	     }
	 }

	}

