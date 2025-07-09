package com.example.weather.util;

import java.util.HashMap;
import java.util.Map;

public class WeatherCodeMapper {
    
    private static final Map<Integer, String> WEATHER_CODE_MAP = new HashMap<>();
    
    static {
        WEATHER_CODE_MAP.put(0, "Cielo sereno");
        WEATHER_CODE_MAP.put(1, "Principalmente sereno");
        WEATHER_CODE_MAP.put(2, "Parzialmente nuvoloso");
        WEATHER_CODE_MAP.put(3, "Nuvoloso");
        WEATHER_CODE_MAP.put(45, "Nebbia");
        WEATHER_CODE_MAP.put(48, "Nebbia con brina");
        WEATHER_CODE_MAP.put(51, "Pioggerella leggera");
        WEATHER_CODE_MAP.put(53, "Pioggerella moderata");
        WEATHER_CODE_MAP.put(55, "Pioggerella intensa");
        WEATHER_CODE_MAP.put(56, "Pioggerella ghiacciata leggera");
        WEATHER_CODE_MAP.put(57, "Pioggerella ghiacciata intensa");
        WEATHER_CODE_MAP.put(61, "Pioggia leggera");
        WEATHER_CODE_MAP.put(63, "Pioggia moderata");
        WEATHER_CODE_MAP.put(65, "Pioggia intensa");
        WEATHER_CODE_MAP.put(66, "Pioggia ghiacciata leggera");
        WEATHER_CODE_MAP.put(67, "Pioggia ghiacciata intensa");
        WEATHER_CODE_MAP.put(71, "Neve leggera");
        WEATHER_CODE_MAP.put(73, "Neve moderata");
        WEATHER_CODE_MAP.put(75, "Neve intensa");
        WEATHER_CODE_MAP.put(77, "Granelli di neve");
        WEATHER_CODE_MAP.put(80, "Rovesci di pioggia leggeri");
        WEATHER_CODE_MAP.put(81, "Rovesci di pioggia moderati");
        WEATHER_CODE_MAP.put(82, "Rovesci di pioggia intensi");
        WEATHER_CODE_MAP.put(85, "Rovesci di neve leggeri");
        WEATHER_CODE_MAP.put(86, "Rovesci di neve intensi");
        WEATHER_CODE_MAP.put(95, "Temporale");
        WEATHER_CODE_MAP.put(96, "Temporale con grandine leggera");
        WEATHER_CODE_MAP.put(99, "Temporale con grandine intensa");
    }
    
    public static String getDescription(int weatherCode) {
        return WEATHER_CODE_MAP.getOrDefault(weatherCode, "Condizioni sconosciute");
    }
}
