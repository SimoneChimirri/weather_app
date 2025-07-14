package com.example.weatherapp.service;

import com.example.weatherapp.dto.WeatherData;
import com.example.weatherapp.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WeatherService {
    
    private final WebClient webClient;
    private static final String OPENMETEO_URL = "https://api.open-meteo.com/v1/forecast";
    
    @Autowired
    public WeatherService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }
    
    public Mono<WeatherData> getWeatherData(City city) {
        String url = String.format("%s?latitude=%f&longitude=%f&current=temperature_2m,relative_humidity_2m,wind_speed_10m,wind_direction_10m&hourly=temperature_2m,relative_humidity_2m,wind_speed_10m&timezone=auto&forecast_days=1",
                OPENMETEO_URL, city.getLatitude(), city.getLongitude());
        
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(WeatherData.class)
                .doOnError(error -> System.err.println("Error fetching weather data: " + error.getMessage()));
    }
}