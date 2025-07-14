package com.example.weatherapp.config;

import com.example.weatherapp.model.City;
import com.example.weatherapp.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private CityRepository cityRepository;
    
    @Override
    public void run(String... args) throws Exception {
        if (cityRepository.count() == 0) {
            // Inizializza con alcune città italiane
            cityRepository.save(new City("Roma", "Italy", 41.9028, 12.4964));
            cityRepository.save(new City("Milano", "Italy", 45.4642, 9.1900));
            cityRepository.save(new City("Napoli", "Italy", 40.8518, 14.2681));
            cityRepository.save(new City("Torino", "Italy", 45.0703, 7.6869));
            cityRepository.save(new City("Firenze", "Italy", 43.7696, 11.2558));
            cityRepository.save(new City("Venezia", "Italy", 45.4408, 12.3155));
            cityRepository.save(new City("Bologna", "Italy", 44.4949, 11.3426));
            cityRepository.save(new City("Genova", "Italy", 44.4056, 8.9463));
            cityRepository.save(new City("Palermo", "Italy", 38.1157, 13.3615));
            cityRepository.save(new City("Bari", "Italy", 41.1171, 16.8719));
            
            // Alcune città europee
            cityRepository.save(new City("London", "United Kingdom", 51.5074, -0.1278));
            cityRepository.save(new City("Paris", "France", 48.8566, 2.3522));
            cityRepository.save(new City("Berlin", "Germany", 52.5200, 13.4050));
            cityRepository.save(new City("Madrid", "Spain", 40.4168, -3.7038));
            cityRepository.save(new City("Amsterdam", "Netherlands", 52.3676, 4.9041));
            
            System.out.println("Database initialized with " + cityRepository.count() + " cities");
        }
    }
}