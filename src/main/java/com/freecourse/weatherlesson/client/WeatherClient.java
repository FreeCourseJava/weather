package com.freecourse.weatherlesson.client;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freecourse.weatherlesson.entity.Weather;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class WeatherClient {
    
    private final RestTemplate restTemplate;
    
    @Value("${weather-api.key}")
    private String key;
    
    private static final String TEMPLATE = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";
    @SneakyThrows
    public Weather getWeather(String city) {
        String response = restTemplate.getForObject(String.format(TEMPLATE, city, key), String.class);
        Weather weather = new Weather();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(response);
        JsonNode mainNode = node.get("main");
        Double temperature = mainNode.get("temp").asDouble();
        weather.setTemperature(temperature);
        weather.setCity(node.get("name").asText());
        return weather;
    }
}
