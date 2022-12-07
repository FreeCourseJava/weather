package com.freecourse.weatherlesson.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.freecourse.weatherlesson.client.WeatherClient;
import com.freecourse.weatherlesson.entity.Weather;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class WeatherController {
    
    private final WeatherClient weatherClient;
    
    @GetMapping("/{city}")
    public String getWeather(@PathVariable("city") String city, Model model) {
        Weather weather = weatherClient.getWeather(city);
        model.addAttribute("weather", weather);
        return "index";
    }
    
}
