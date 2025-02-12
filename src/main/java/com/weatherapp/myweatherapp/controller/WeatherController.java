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
  public ResponseEntity<CityInfo> compareDaylightHours(@PathVariable("city1") String city1, @PathVariable("city2") String city2) {

    // city 1: sunset - sunrise
    // city 2: sunset - sunrise
    // daylight1 < daylight2, return city2
    // else return city1

    CityInfo ci1 = weatherService.forecastByCity(city1);
    CityInfo ci2 = weatherService.forecastByCity(city2);


    int daylightHours1 = weatherService.getDaylightHours(city1);
    int daylightHours2 = weatherService.getDaylightHours(city2);

    if(daylightHours1 > daylightHours2){
      return ResponseEntity.ok(ci1);
    }
    else{
      return ResponseEntity.ok(ci2);
    }
    
  }

  // TODO: given two city names, check which city its currently raining in

}
