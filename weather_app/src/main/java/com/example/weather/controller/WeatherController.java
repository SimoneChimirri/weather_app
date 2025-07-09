package com.example.weather.controller;

import com.example.weather.dto.WeatherResponse;
import com.example.weather.service.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    
    private final WeatherService weatherService;
    
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }
    
    @GetMapping("/coordinates")
    public ResponseEntity<WeatherResponse> getWeatherByCoordinates(
            @RequestParam @NotNull @DecimalMin(value = "-90.0", message = "Latitude must be between -90 and 90")
            @DecimalMax(value = "90.0", message = "Latitude must be between -90 and 90") Double latitude,
            @RequestParam @NotNull @DecimalMin(value = "-180.0", message = "Longitude must be between -180 and 180")
            @DecimalMax(value = "180.0", message = "Longitude must be between -180 and 180") Double longitude) {
        
        try {
            WeatherResponse weather = weatherService.getWeatherByCoordinates(latitude, longitude);
            return ResponseEntity.ok(weather);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(createErrorResponse(e.getMessage()));
        }
    }
    
    @GetMapping("/city/{cityName}")
    public ResponseEntity<WeatherResponse> getWeatherByCity(
            @PathVariable @NotBlank(message = "City name cannot be blank") String cityName) {
        
        try {
            WeatherResponse weather = weatherService.getWeatherByCity(cityName);
            return ResponseEntity.ok(weather);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(createErrorResponse("City not found: " + cityName));
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(createErrorResponse(e.getMessage()));
        }
    }
    
    @GetMapping("/current")
    public ResponseEntity<WeatherResponse.CurrentWeatherInfo> getCurrentWeather(
            @RequestParam @NotNull Double latitude,
            @RequestParam @NotNull Double longitude) {
        
        try {
            WeatherResponse weather = weatherService.getWeatherByCoordinates(latitude, longitude);
            return ResponseEntity.ok(weather.getCurrent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/forecast/hourly")
    public ResponseEntity<?> getHourlyForecast(
            @RequestParam @NotNull Double latitude,
            @RequestParam @NotNull Double longitude) {
        
        try {
            WeatherResponse weather = weatherService.getWeatherByCoordinates(latitude, longitude);
            return ResponseEntity.ok(weather.getHourlyForecast());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error fetching hourly forecast: " + e.getMessage());
        }
    }
    
    @GetMapping("/forecast/daily")
    public ResponseEntity<?> getDailyForecast(
            @RequestParam @NotNull Double latitude,
            @RequestParam @NotNull Double longitude) {
        
        try {
            WeatherResponse weather = weatherService.getWeatherByCoordinates(latitude, longitude);
            return ResponseEntity.ok(weather.getDailyForecast());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error fetching daily forecast: " + e.getMessage());
        }
    }
    
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Weather API is running");
    }
    
    private WeatherResponse createErrorResponse(String errorMessage) {
        WeatherResponse errorResponse = new WeatherResponse();
        errorResponse.setLocation("Error: " + errorMessage);
        return errorResponse;
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("An error occurred: " + e.getMessage());
    }
}

