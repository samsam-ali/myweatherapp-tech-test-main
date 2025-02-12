package com.weatherapp.myweatherapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WeatherController {

  @Autowired
  WeatherService weatherService;

  @GetMapping("/forecast/{city}")
  public ResponseEntity<CityInfo> forecastByCity(@PathVariable("city") String city) {

    CityInfo ci = weatherService.forecastByCity(city);

    return ResponseEntity.ok(ci);
  }

  // TODO: given two city names, compare the length of the daylight hours and return the city with the longest day
  
  @GetMapping("/daylight/{city1}/{city2}")
  public ResponseEntity<String> compareDaylightHours(@PathVariable("city1") String city1, @PathVariable("city2") String city2) {

    // CityInfo ci1 = weatherService.forecastByCity(city1);
    // CityInfo ci2 = weatherService.forecastByCity(city2);

    double daylightHours1 = weatherService.getDaylightHours(city1);
    double daylightHours2 = weatherService.getDaylightHours(city2);
    String message = "";
    // String message = city1 + " daylight hours are " + daylightHours1 + ", while " + city2 + " daylight hours are " + daylightHours2 + ". ";

    if(daylightHours1 > daylightHours2){
      message = city1 + " has the longest daylight hours.";
    }
    else if(daylightHours1 < daylightHours2){
      message = city2 + " has the longest daylight hours.";
    }
    else{
      message = "Both cities have the same daylight hours.";
    }

    return ResponseEntity.ok(message);
    
  }

  // TODO: given two city names, check which city its currently raining in

}
