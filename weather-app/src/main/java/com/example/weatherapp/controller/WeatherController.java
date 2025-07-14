package com.example.weatherapp.controller;

import com.example.weatherapp.dto.WeatherData;
import com.example.weatherapp.model.City;
import com.example.weatherapp.service.CityService;
import com.example.weatherapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
public class WeatherController {
    
    @Autowired
    private CityService cityService;
    
    @Autowired
    private WeatherService weatherService;
    
    @GetMapping("/")
    public String index(Model model) {
        List<City> cities = cityService.getAllCities();
        model.addAttribute("cities", cities);
        return "index";
    }
    
    @GetMapping("/weather/{cityId}")
    public String getWeatherPage(@PathVariable Long cityId, Model model) {
        City city = cityService.getCityById(cityId)
                .orElseThrow(() -> new RuntimeException("City not found"));
        
        model.addAttribute("city", city);
        return "weather";
    }
    
    @GetMapping("/api/weather/{cityId}")
    @ResponseBody
    public Mono<ResponseEntity<WeatherData>> getWeatherData(@PathVariable Long cityId) {
        return cityService.getCityById(cityId)
                .map(city -> weatherService.getWeatherData(city)
                        .map(ResponseEntity::ok)
                        .defaultIfEmpty(ResponseEntity.notFound().build()))
                .orElse(Mono.just(ResponseEntity.notFound().build()));
    }
    
    @GetMapping("/api/cities")
    @ResponseBody
    public ResponseEntity<List<City>> getCities() {
        return ResponseEntity.ok(cityService.getAllCities());
    }
    
    @PostMapping("/api/cities")
    @ResponseBody
    public ResponseEntity<City> addCity(@RequestBody City city) {
        City savedCity = cityService.saveCity(city);
        return ResponseEntity.ok(savedCity);
    }
    
    @DeleteMapping("/api/cities/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
        return ResponseEntity.ok().build();
    }
}