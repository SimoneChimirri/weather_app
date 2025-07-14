package com.example.weatherapp.repository;

import com.example.weatherapp.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findByNameContainingIgnoreCase(String name);
    List<City> findByCountryContainingIgnoreCase(String country);
}