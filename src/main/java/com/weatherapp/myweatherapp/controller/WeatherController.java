package com.weatherapp.myweatherapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.service.WeatherService;

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
    try{
      CityInfo ci1 = weatherService.forecastByCity(city1);
      CityInfo ci2 = weatherService.forecastByCity(city2);

      String message = weatherService.compareDaylightHours(ci1, ci2, city1, city2);

      return ResponseEntity.ok(message);
    }catch (Exception e) {
      return ResponseEntity.status(500).body("Error comparing daylight hours: " + e.getMessage());
    }
  }

  // TODO: given two city names, check which city its currently raining in
  @GetMapping("/rainfall/{city1}/{city2}")
  public ResponseEntity<String> rainCheck(@PathVariable("city1") String city1, @PathVariable("city2") String city2){
    try{
      CityInfo ci1 = weatherService.forecastByCity(city1);
      CityInfo ci2 = weatherService.forecastByCity(city2);

      String message = weatherService.rainCheck(ci1, ci2, city1, city2);
      return ResponseEntity.ok(message);
    }catch (Exception e) {
      return ResponseEntity.status(500).body("Error checking where it is raining: " + e.getMessage());
    }
  }



}
