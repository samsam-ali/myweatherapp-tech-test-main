package com.weatherapp.myweatherapp.service;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.repository.VisualcrossingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

  @Autowired
  VisualcrossingRepository weatherRepo;

  public CityInfo forecastByCity(String city) {

    return weatherRepo.getByCity(city);
  }

  // public int getDaylightHours(String city){
  //   CityInfo ci = forecastByCity(city);

  //   // get sunset
  //   String sunset = ci.getCurrentConditions().getSunset();

  //   // get sunrise
  //   String sunrise = ci.getCurrentConditions().getSunrise();

  //   // get hours
  //   int sunriseHour = sunrise.charAt(1);
  //   int sunsetHour = sunset.charAt(1) + 10;

  //   return sunsetHour-sunriseHour;
  // }

  public double getDaylightHours(String city){
    CityInfo ci = forecastByCity(city);

    String sunset = ci.getCurrentConditions().getSunset();
    String sunrise = ci.getCurrentConditions().getSunrise();

    String[] sunsetList = sunset.split(":");
    String[] sunriseList = sunrise.split(":");

    // find the daylight hours
    double sunsetMin = Double.parseDouble(sunsetList[1]);
    double sunsetHour = Double.parseDouble(sunsetList[0]) + (sunsetMin / 60);

    double sunriseMin = Double.parseDouble(sunriseList[1]);
    double sunriseHour = Double.parseDouble(sunriseList[0]) + (sunriseMin / 60);

    double daylightHours = sunsetHour - sunriseHour;

    return daylightHours;
  }
}
