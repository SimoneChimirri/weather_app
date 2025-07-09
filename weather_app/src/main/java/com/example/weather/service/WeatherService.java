package com.example.weather.service;

import com.example.weather.dto.WeatherDto;
import com.example.weather.dto.WeatherResponse;
import com.example.weather.util.WeatherCodeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {
    
    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);
    
    private final WebClient webClient;
    private final String apiUrl;
    
    public WeatherService(WebClient webClient, @Value("${openmeteo.api.url}") String apiUrl) {
        this.webClient = webClient;
        this.apiUrl = apiUrl;
    }
    
    @Cacheable(value = "weather", key = "#latitude + '_' + #longitude")
    public WeatherResponse getWeatherByCoordinates(double latitude, double longitude) {
        logger.info("Fetching weather data for coordinates: {}, {}", latitude, longitude);
        
        try {
            WeatherDto weatherDto = webClient.get()
                .uri(uriBuilder -> uriBuilder
                    .path("")
                    .queryParam("latitude", latitude)
                    .queryParam("longitude", longitude)
                    .queryParam("current_weather", true)
                    .queryParam("hourly", "temperature_2m,relative_humidity_2m,precipitation_probability,wind_speed_10m")
                    .queryParam("daily", "temperature_2m_max,temperature_2m_min,precipitation_probability_max")
                    .queryParam("timezone", "auto")
                    .build())
                .retrieve()
                .bodyToMono(WeatherDto.class)
                .timeout(Duration.ofSeconds(10))
                .block();
            
            if (weatherDto == null) {
                throw new RuntimeException("No weather data received");
            }
            
            return convertToWeatherResponse(weatherDto);
            
        } catch (Exception e) {
            logger.error("Error fetching weather data: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch weather data: " + e.getMessage());
        }
    }
    
    @Cacheable(value = "weather", key = "#cityName")
    public WeatherResponse getWeatherByCity(String cityName) {
        // Per semplicità, useremo coordinate predefinite per alcune città
        // In un'applicazione reale, dovresti usare un servizio di geocoding
        double[] coordinates = getCityCoordinates(cityName);
        if (coordinates == null) {
            throw new RuntimeException("City not found: " + cityName);
        }
        
        WeatherResponse response = getWeatherByCoordinates(coordinates[0], coordinates[1]);
        response.setLocation(cityName);
        return response;
    }
    
    private double[] getCityCoordinates(String cityName) {
        // Coordinate predefinite per alcune città italiane
        switch (cityName.toLowerCase()) {
            case "milano":
            case "milan":
                return new double[]{45.4642, 9.1900};
            case "roma":
            case "rome":
                return new double[]{41.9028, 12.4964};
            case "napoli":
            case "naples":
                return new double[]{40.8518, 14.2681};
            case "torino":
            case "turin":
                return new double[]{45.0703, 7.6869};
            case "firenze":
            case "florence":
                return new double[]{43.7696, 11.2558};
            case "bologna":
                return new double[]{44.4949, 11.3426};
            case "venezia":
            case "venice":
                return new double[]{45.4408, 12.3155};
            case "genova":
            case "genoa":
                return new double[]{44.4056, 8.9463};
            case "palermo":
                return new double[]{38.1157, 13.3615};
            case "bari":
                return new double[]{41.1171, 16.8719};
            default:
                return null;
        }
    }
    
    private WeatherResponse convertToWeatherResponse(WeatherDto weatherDto) {
        WeatherResponse response = new WeatherResponse();
        response.setLatitude(weatherDto.getLatitude());
        response.setLongitude(weatherDto.getLongitude());
        response.setTimezone(weatherDto.getTimezone());
        
        // Convert current weather
        if (weatherDto.getCurrentWeather() != null) {
            WeatherDto.CurrentWeather current = weatherDto.getCurrentWeather();
            WeatherResponse.CurrentWeatherInfo currentInfo = new WeatherResponse.CurrentWeatherInfo(
                current.getTemperature(),
                current.getWindspeed(),
                current.getWinddirection(),
                current.getWeathercode(),
                WeatherCodeMapper.getDescription(current.getWeathercode()),
                current.getTime()
            );
            response.setCurrent(currentInfo);
        }
        
        // Convert hourly forecast (next 24 hours)
        if (weatherDto.getHourly() != null) {
            List<WeatherResponse.HourlyForecast> hourlyList = new ArrayList<>();
            WeatherDto.HourlyData hourly = weatherDto.getHourly();
            
            int maxHours = Math.min(24, hourly.getTime().size());
            for (int i = 0; i < maxHours; i++) {
                WeatherResponse.HourlyForecast forecast = new WeatherResponse.HourlyForecast(
                    hourly.getTime().get(i),
                    hourly.getTemperature2m().get(i),
                    hourly.getRelativeHumidity2m().get(i),
                    hourly.getPrecipitationProbability().get(i),
                    hourly.getWindSpeed10m().get(i)
                );
                hourlyList.add(forecast);
            }
            response.setHourlyForecast(hourlyList);
        }
        
        // Convert daily forecast
        if (weatherDto.getDaily() != null) {
            List<WeatherResponse.DailyForecast> dailyList = new ArrayList<>();
            WeatherDto.DailyData daily = weatherDto.getDaily();
            
            for (int i = 0; i < daily.getTime().size(); i++) {
                WeatherResponse.DailyForecast forecast = new WeatherResponse.DailyForecast(
                    daily.getTime().get(i),
                    daily.getTemperature2mMax().get(i),
                    daily.getTemperature2mMin().get(i),
                    daily.getPrecipitationProbabilityMax().get(i)
                );
                dailyList.add(forecast);
            }
            response.setDailyForecast(dailyList);
        }
        
        return response;
    }
}