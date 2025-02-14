package com.weatherapp.myweatherapp.service;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.repository.VisualcrossingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class WeatherService {

  @Autowired
  VisualcrossingRepository weatherRepo;

  public CityInfo forecastByCity(String city) {

    return weatherRepo.getByCity(city);
  }

  public double getDaylightHours(CityInfo ci){
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

  public String compareDaylightHours(CityInfo ci1, CityInfo ci2, String city1, String city2){
      double daylightHours1 = getDaylightHours(ci1);
      double daylightHours2 = getDaylightHours(ci2);
      String message;

      if(daylightHours1 > daylightHours2){
        message = city1 + " has the longest daylight hours.";
      }
      else if(daylightHours1 < daylightHours2){
        message = city2 + " has the longest daylight hours.";
      }
      else{
        message = "Both cities have the same daylight hours.";
      }
      return message;
  }

  public boolean isRaining(CityInfo ci){
    String conditions = ci.getCurrentConditions().getConditions();
    String[] conditionsList = conditions.split(", ");

    for(int i=0; i<conditionsList.length; i++){
      if(conditionsList[i].equals("Rain")){
        return true;
      }
    }

    return false;
  }

  public String rainCheck(CityInfo ci1, CityInfo ci2, String city1, String city2){
    String message = "";
    Boolean isRaining1 = isRaining(ci1);
    Boolean isRaining2 = isRaining(ci2);

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

    return message;
  }
}
