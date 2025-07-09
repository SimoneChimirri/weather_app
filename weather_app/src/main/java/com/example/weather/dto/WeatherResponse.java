package com.example.weather.dto;

import java.util.List;

public class WeatherResponse {
    private String location;
    private double latitude;
    private double longitude;
    private String timezone;
    private CurrentWeatherInfo current;
    private List<HourlyForecast> hourlyForecast;
    private List<DailyForecast> dailyForecast;
    
    // Constructors
    public WeatherResponse() {}
    
    public WeatherResponse(String location, double latitude, double longitude, String timezone) {
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timezone = timezone;
    }
    
    // Getters and Setters
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    
    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
    
    public String getTimezone() { return timezone; }
    public void setTimezone(String timezone) { this.timezone = timezone; }
    
    public CurrentWeatherInfo getCurrent() { return current; }
    public void setCurrent(CurrentWeatherInfo current) { this.current = current; }
    
    public List<HourlyForecast> getHourlyForecast() { return hourlyForecast; }
    public void setHourlyForecast(List<HourlyForecast> hourlyForecast) { this.hourlyForecast = hourlyForecast; }
    
    public List<DailyForecast> getDailyForecast() { return dailyForecast; }
    public void setDailyForecast(List<DailyForecast> dailyForecast) { this.dailyForecast = dailyForecast; }
    
    // Inner Classes
    public static class CurrentWeatherInfo {
        private double temperature;
        private double windSpeed;
        private int windDirection;
        private int weatherCode;
        private String weatherDescription;
        private String time;
        
        // Constructors
        public CurrentWeatherInfo() {}
        
        public CurrentWeatherInfo(double temperature, double windSpeed, int windDirection, int weatherCode, String weatherDescription, String time) {
            this.temperature = temperature;
            this.windSpeed = windSpeed;
            this.windDirection = windDirection;
            this.weatherCode = weatherCode;
            this.weatherDescription = weatherDescription;
            this.time = time;
        }
        
        // Getters and Setters
        public double getTemperature() { return temperature; }
        public void setTemperature(double temperature) { this.temperature = temperature; }
        
        public double getWindSpeed() { return windSpeed; }
        public void setWindSpeed(double windSpeed) { this.windSpeed = windSpeed; }
        
        public int getWindDirection() { return windDirection; }
        public void setWindDirection(int windDirection) { this.windDirection = windDirection; }
        
        public int getWeatherCode() { return weatherCode; }
        public void setWeatherCode(int weatherCode) { this.weatherCode = weatherCode; }
        
        public String getWeatherDescription() { return weatherDescription; }
        public void setWeatherDescription(String weatherDescription) { this.weatherDescription = weatherDescription; }
        
        public String getTime() { return time; }
        public void setTime(String time) { this.time = time; }
    }
    
    public static class HourlyForecast {
        private String time;
        private double temperature;
        private int humidity;
        private int precipitationProbability;
        private double windSpeed;
        
        // Constructors
        public HourlyForecast() {}
        
        public HourlyForecast(String time, double temperature, int humidity, int precipitationProbability, double windSpeed) {
            this.time = time;
            this.temperature = temperature;
            this.humidity = humidity;
            this.precipitationProbability = precipitationProbability;
            this.windSpeed = windSpeed;
        }
        
        // Getters and Setters
        public String getTime() { return time; }
        public void setTime(String time) { this.time = time; }
        
        public double getTemperature() { return temperature; }
        public void setTemperature(double temperature) { this.temperature = temperature; }
        
        public int getHumidity() { return humidity; }
        public void setHumidity(int humidity) { this.humidity = humidity; }
        
        public int getPrecipitationProbability() { return precipitationProbability; }
        public void setPrecipitationProbability(int precipitationProbability) { this.precipitationProbability = precipitationProbability; }
        
        public double getWindSpeed() { return windSpeed; }
        public void setWindSpeed(double windSpeed) { this.windSpeed = windSpeed; }
    }
    
    public static class DailyForecast {
        private String date;
        private double temperatureMax;
        private double temperatureMin;
        private int precipitationProbability;
        
        // Constructors
        public DailyForecast() {}
        
        public DailyForecast(String date, double temperatureMax, double temperatureMin, int precipitationProbability) {
            this.date = date;
            this.temperatureMax = temperatureMax;
            this.temperatureMin = temperatureMin;
            this.precipitationProbability = precipitationProbability;
        }
        
        // Getters and Setters
        public String getDate() { return date; }
        public void setDate(String date) { this.date = date; }
        
        public double getTemperatureMax() { return temperatureMax; }
        public void setTemperatureMax(double temperatureMax) { this.temperatureMax = temperatureMax; }
        
        public double getTemperatureMin() { return temperatureMin; }
        public void setTemperatureMin(double temperatureMin) { this.temperatureMin = temperatureMin; }
        
        public int getPrecipitationProbability() { return precipitationProbability; }
        public void setPrecipitationProbability(int precipitationProbability) { this.precipitationProbability = precipitationProbability; }
    }
}
