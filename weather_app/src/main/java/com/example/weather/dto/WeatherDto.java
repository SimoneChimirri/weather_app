package com.example.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;

public class WeatherDto {
    
    private double latitude;
    private double longitude;
    @JsonProperty("generationtime_ms")
    private double generationTimeMs;
    @JsonProperty("utc_offset_seconds")
    private int utcOffsetSeconds;
    private String timezone;
    @JsonProperty("timezone_abbreviation")
    private String timezoneAbbreviation;
    private int elevation;
    @JsonProperty("current_weather")
    private CurrentWeather currentWeather;
    @JsonProperty("hourly_units")
    private HourlyUnits hourlyUnits;
    private HourlyData hourly;
    @JsonProperty("daily_units")
    private DailyUnits dailyUnits;
    private DailyData daily;
    
    // Constructors
    public WeatherDto() {}
    
    // Getters and Setters
    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    
    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
    
    public double getGenerationTimeMs() { return generationTimeMs; }
    public void setGenerationTimeMs(double generationTimeMs) { this.generationTimeMs = generationTimeMs; }
    
    public int getUtcOffsetSeconds() { return utcOffsetSeconds; }
    public void setUtcOffsetSeconds(int utcOffsetSeconds) { this.utcOffsetSeconds = utcOffsetSeconds; }
    
    public String getTimezone() { return timezone; }
    public void setTimezone(String timezone) { this.timezone = timezone; }
    
    public String getTimezoneAbbreviation() { return timezoneAbbreviation; }
    public void setTimezoneAbbreviation(String timezoneAbbreviation) { this.timezoneAbbreviation = timezoneAbbreviation; }
    
    public int getElevation() { return elevation; }
    public void setElevation(int elevation) { this.elevation = elevation; }
    
    public CurrentWeather getCurrentWeather() { return currentWeather; }
    public void setCurrentWeather(CurrentWeather currentWeather) { this.currentWeather = currentWeather; }
    
    public HourlyUnits getHourlyUnits() { return hourlyUnits; }
    public void setHourlyUnits(HourlyUnits hourlyUnits) { this.hourlyUnits = hourlyUnits; }
    
    public HourlyData getHourly() { return hourly; }
    public void setHourly(HourlyData hourly) { this.hourly = hourly; }
    
    public DailyUnits getDailyUnits() { return dailyUnits; }
    public void setDailyUnits(DailyUnits dailyUnits) { this.dailyUnits = dailyUnits; }
    
    public DailyData getDaily() { return daily; }
    public void setDaily(DailyData daily) { this.daily = daily; }
    
    // Inner Classes
    public static class CurrentWeather {
        private double temperature;
        private double windspeed;
        private int winddirection;
        private int weathercode;
        private String time;
        
        // Getters and Setters
        public double getTemperature() { return temperature; }
        public void setTemperature(double temperature) { this.temperature = temperature; }
        
        public double getWindspeed() { return windspeed; }
        public void setWindspeed(double windspeed) { this.windspeed = windspeed; }
        
        public int getWinddirection() { return winddirection; }
        public void setWinddirection(int winddirection) { this.winddirection = winddirection; }
        
        public int getWeathercode() { return weathercode; }
        public void setWeathercode(int weathercode) { this.weathercode = weathercode; }
        
        public String getTime() { return time; }
        public void setTime(String time) { this.time = time; }
    }
    
    public static class HourlyUnits {
        private String time;
        @JsonProperty("temperature_2m")
        private String temperature2m;
        @JsonProperty("relative_humidity_2m")
        private String relativeHumidity2m;
        @JsonProperty("precipitation_probability")
        private String precipitationProbability;
        @JsonProperty("wind_speed_10m")
        private String windSpeed10m;
        
        // Getters and Setters
        public String getTime() { return time; }
        public void setTime(String time) { this.time = time; }
        
        public String getTemperature2m() { return temperature2m; }
        public void setTemperature2m(String temperature2m) { this.temperature2m = temperature2m; }
        
        public String getRelativeHumidity2m() { return relativeHumidity2m; }
        public void setRelativeHumidity2m(String relativeHumidity2m) { this.relativeHumidity2m = relativeHumidity2m; }
        
        public String getPrecipitationProbability() { return precipitationProbability; }
        public void setPrecipitationProbability(String precipitationProbability) { this.precipitationProbability = precipitationProbability; }
        
        public String getWindSpeed10m() { return windSpeed10m; }
        public void setWindSpeed10m(String windSpeed10m) { this.windSpeed10m = windSpeed10m; }
    }
    
    public static class HourlyData {
        private List<String> time;
        @JsonProperty("temperature_2m")
        private List<Double> temperature2m;
        @JsonProperty("relative_humidity_2m")
        private List<Integer> relativeHumidity2m;
        @JsonProperty("precipitation_probability")
        private List<Integer> precipitationProbability;
        @JsonProperty("wind_speed_10m")
        private List<Double> windSpeed10m;
        
        // Getters and Setters
        public List<String> getTime() { return time; }
        public void setTime(List<String> time) { this.time = time; }
        
        public List<Double> getTemperature2m() { return temperature2m; }
        public void setTemperature2m(List<Double> temperature2m) { this.temperature2m = temperature2m; }
        
        public List<Integer> getRelativeHumidity2m() { return relativeHumidity2m; }
        public void setRelativeHumidity2m(List<Integer> relativeHumidity2m) { this.relativeHumidity2m = relativeHumidity2m; }
        
        public List<Integer> getPrecipitationProbability() { return precipitationProbability; }
        public void setPrecipitationProbability(List<Integer> precipitationProbability) { this.precipitationProbability = precipitationProbability; }
        
        public List<Double> getWindSpeed10m() { return windSpeed10m; }
        public void setWindSpeed10m(List<Double> windSpeed10m) { this.windSpeed10m = windSpeed10m; }
    }
    
    public static class DailyUnits {
        private String time;
        @JsonProperty("temperature_2m_max")
        private String temperature2mMax;
        @JsonProperty("temperature_2m_min")
        private String temperature2mMin;
        @JsonProperty("precipitation_probability_max")
        private String precipitationProbabilityMax;
        
        // Getters and Setters
        public String getTime() { return time; }
        public void setTime(String time) { this.time = time; }
        
        public String getTemperature2mMax() { return temperature2mMax; }
        public void setTemperature2mMax(String temperature2mMax) { this.temperature2mMax = temperature2mMax; }
        
        public String getTemperature2mMin() { return temperature2mMin; }
        public void setTemperature2mMin(String temperature2mMin) { this.temperature2mMin = temperature2mMin; }
        
        public String getPrecipitationProbabilityMax() { return precipitationProbabilityMax; }
        public void setPrecipitationProbabilityMax(String precipitationProbabilityMax) { this.precipitationProbabilityMax = precipitationProbabilityMax; }
    }
    
    public static class DailyData {
        private List<String> time;
        @JsonProperty("temperature_2m_max")
        private List<Double> temperature2mMax;
        @JsonProperty("temperature_2m_min")
        private List<Double> temperature2mMin;
        @JsonProperty("precipitation_probability_max")
        private List<Integer> precipitationProbabilityMax;
        
        // Getters and Setters
        public List<String> getTime() { return time; }
        public void setTime(List<String> time) { this.time = time; }
        
        public List<Double> getTemperature2mMax() { return temperature2mMax; }
        public void setTemperature2mMax(List<Double> temperature2mMax) { this.temperature2mMax = temperature2mMax; }
        
        public List<Double> getTemperature2mMin() { return temperature2mMin; }
        public void setTemperature2mMin(List<Double> temperature2mMin) { this.temperature2mMin = temperature2mMin; }
        
        public List<Integer> getPrecipitationProbabilityMax() { return precipitationProbabilityMax; }
        public void setPrecipitationProbabilityMax(List<Integer> precipitationProbabilityMax) { this.precipitationProbabilityMax = precipitationProbabilityMax; }
    }
}
