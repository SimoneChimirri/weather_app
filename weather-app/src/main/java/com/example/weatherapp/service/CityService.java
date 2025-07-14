package com.example.weatherapp.service;

import com.example.weatherapp.model.City;
import com.example.weatherapp.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    
    @Autowired
    private CityRepository cityRepository;
    
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
    
    public Optional<City> getCityById(Long id) {
        return cityRepository.findById(id);
    }
    
    public City saveCity(City city) {
        return cityRepository.save(city);
    }
    
    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }
    
    public List<City> searchCitiesByName(String name) {
        return cityRepository.findByNameContainingIgnoreCase(name);
    }
    
    public List<City> searchCitiesByCountry(String country) {
        return cityRepository.findByCountryContainingIgnoreCase(country);
    }
}