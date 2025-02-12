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
      double daylightHours1 = weatherService.getDaylightHours(city1);
      double daylightHours2 = weatherService.getDaylightHours(city2);
      String message = "";

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
    }catch (Exception e) {
      return ResponseEntity.status(500).body("Error comparing daylight hours: " + e.getMessage());
    }
  }

  // TODO: given two city names, check which city its currently raining in
  @GetMapping("/rainfall/{city1}/{city2}")
  public ResponseEntity<String> rainCheck(@PathVariable("city1") String city1, @PathVariable("city2") String city2){
    try{
      String message = "";
      Boolean isRaining1 = weatherService.isRaining(city1);
      Boolean isRaining2 = weatherService.isRaining(city2);

      if(isRaining1 & isRaining2){
        message = "It is currently raining in both cities.";
      }
      else if(isRaining1){
        message = "It is currently raining in " + city1 + ".";
      }
      else if(isRaining2){
        message = "It is currently raining in " + city2 + ".";
      }
      else{
        message = "It is currently not raining in any of the cities.";
      }

      return ResponseEntity.ok(message);
    }catch (Exception e) {
      return ResponseEntity.status(500).body("Error checking where it is raining: " + e.getMessage());
    }

    
  }



}
