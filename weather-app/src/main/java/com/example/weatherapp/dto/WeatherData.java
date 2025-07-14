package com.example.weatherapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class WeatherData {
    private Current current;
    private Hourly hourly;
    
    public static class Current {
        private String time;
        private Double temperature_2m;
        private Double relative_humidity_2m;
        private Double wind_speed_10m;
        private Double wind_direction_10m;
        
        // Getters and Setters
        public String getTime() { return time; }
        public void setTime(String time) { this.time = time; }
        
        @JsonProperty("temperature_2m")
        public Double getTemperature() { return temperature_2m; }
        public void setTemperature(Double temperature) { this.temperature_2m = temperature; }
        
        @JsonProperty("relative_humidity_2m")
        public Double getHumidity() { return relative_humidity_2m; }
        public void setHumidity(Double humidity) { this.relative_humidity_2m = humidity; }
        
        @JsonProperty("wind_speed_10m")
        public Double getWindSpeed() { return wind_speed_10m; }
        public void setWindSpeed(Double windSpeed) { this.wind_speed_10m = windSpeed; }
        
        @JsonProperty("wind_direction_10m")
        public Double getWindDirection() { return wind_direction_10m; }
        public void setWindDirection(Double windDirection) { this.wind_direction_10m = windDirection; }
    }
    
    public static class Hourly {
        private List<String> time;
        private List<Double> temperature_2m;
        private List<Double> relative_humidity_2m;
        private List<Double> wind_speed_10m;
        
        // Getters and Setters
        public List<String> getTime() { return time; }
        public void setTime(List<String> time) { this.time = time; }
        
        @JsonProperty("temperature_2m")
        public List<Double> getTemperature() { return temperature_2m; }
        public void setTemperature(List<Double> temperature) { this.temperature_2m = temperature; }
        
        @JsonProperty("relative_humidity_2m")
        public List<Double> getHumidity() { return relative_humidity_2m; }
        public void setHumidity(List<Double> humidity) { this.relative_humidity_2m = humidity; }
        
        @JsonProperty("wind_speed_10m")
        public List<Double> getWindSpeed() { return wind_speed_10m; }
        public void setWindSpeed(List<Double> windSpeed) { this.wind_speed_10m = windSpeed; }
    }
    
    // Getters and Setters
    public Current getCurrent() { return current; }
    public void setCurrent(Current current) { this.current = current; }
    
    public Hourly getHourly() { return hourly; }
    public void setHourly(Hourly hourly) { this.hourly = hourly; }
}