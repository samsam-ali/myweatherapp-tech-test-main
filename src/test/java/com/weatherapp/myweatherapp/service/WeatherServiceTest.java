package com.weatherapp.myweatherapp.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.weatherapp.myweatherapp.controller.WeatherController;
import com.weatherapp.myweatherapp.model.CityInfo;


class WeatherServiceTest {

  // TODO: 12/05/2023 write unit tests
  @Mock
  WeatherService weatherService;
  @InjectMocks
  WeatherController weatherController;
  CityInfo london;
  CityInfo berlin;

  

  @BeforeEach
  void setUp() {
    try{
      String jsonLondon = "{\"address\":\"london\",\"description\":\"Similar temperatures continuing with no rain expected.\",\"currentConditions\":{\"temp\":\"39.6\",\"sunrise\":\"07:17:52\",\"sunset\":\"17:12:25\",\"feelslike\":\"37.5\",\"humidity\":\"82.4\",\"conditions\":\"Overcast\"},\"days\":[{\"datetime\":\"2025-02-13\",\"temp\":\"39.0\",\"tempmax\":\"41.4\",\"tempmin\":\"35.5\",\"conditions\":\"Overcast\",\"description\":\"Cloudy skies throughout the day.\"},{\"datetime\":\"2025-02-14\",\"temp\":\"35.7\",\"tempmax\":\"39.5\",\"tempmin\":\"32.8\",\"conditions\":\"Partially cloudy\",\"description\":\"Partly cloudy throughout the day.\"},{\"datetime\":\"2025-02-15\",\"temp\":\"37.9\",\"tempmax\":\"42.3\",\"tempmin\":\"35.3\",\"conditions\":\"Overcast\",\"description\":\"Cloudy skies throughout the day.\"},{\"datetime\":\"2025-02-16\",\"temp\":\"38.4\",\"tempmax\":\"42.0\",\"tempmin\":\"35.5\",\"conditions\":\"Overcast\",\"description\":\"Cloudy skies throughout the day.\"},{\"datetime\":\"2025-02-17\",\"temp\":\"40.0\",\"tempmax\":\"46.3\",\"tempmin\":\"34.4\",\"conditions\":\"Partially cloudy\",\"description\":\"Partly cloudy throughout the day.\"},{\"datetime\":\"2025-02-18\",\"temp\":\"36.7\",\"tempmax\":\"42.2\",\"tempmin\":\"32.3\",\"conditions\":\"Partially cloudy\",\"description\":\"Partly cloudy throughout the day.\"},{\"datetime\":\"2025-02-19\",\"temp\":\"38.0\",\"tempmax\":\"42.7\",\"tempmin\":\"35.0\",\"conditions\":\"Overcast\",\"description\":\"Cloudy skies throughout the day.\"},{\"datetime\":\"2025-02-20\",\"temp\":\"43.7\",\"tempmax\":\"50.8\",\"tempmin\":\"37.1\",\"conditions\":\"Overcast\",\"description\":\"Cloudy skies throughout the day.\"},{\"datetime\":\"2025-02-21\",\"temp\":\"48.7\",\"tempmax\":\"49.5\",\"tempmin\":\"47.6\",\"conditions\":\"Overcast\",\"description\":\"Cloudy skies throughout the day.\"},{\"datetime\":\"2025-02-22\",\"temp\":\"51.1\",\"tempmax\":\"56.4\",\"tempmin\":\"47.7\",\"conditions\":\"Partially cloudy\",\"description\":\"Partly cloudy throughout the day.\"},{\"datetime\":\"2025-02-23\",\"temp\":\"49.6\",\"tempmax\":\"50.8\",\"tempmin\":\"48.4\",\"conditions\":\"Overcast\",\"description\":\"Cloudy skies throughout the day.\"},{\"datetime\":\"2025-02-24\",\"temp\":\"48.1\",\"tempmax\":\"51.5\",\"tempmin\":\"45.0\",\"conditions\":\"Overcast\",\"description\":\"Cloudy skies throughout the day.\"},{\"datetime\":\"2025-02-25\",\"temp\":\"48.3\",\"tempmax\":\"52.1\",\"tempmin\":\"44.5\",\"conditions\":\"Rain, Partially cloudy\",\"description\":\"Becoming cloudy in the afternoon with a chance of rain.\"},{\"datetime\":\"2025-02-26\",\"temp\":\"46.4\",\"tempmax\":\"48.1\",\"tempmin\":\"43.2\",\"conditions\":\"Overcast\",\"description\":\"Cloudy skies throughout the day.\"},{\"datetime\":\"2025-02-27\",\"temp\":\"50.7\",\"tempmax\":\"54.9\",\"tempmin\":\"47.9\",\"conditions\":\"Partially cloudy\",\"description\":\"Partly cloudy throughout the day.\"}]}";
      String jsonBerlin = "{\"address\":\"berlin\",\"description\":\"Similar temperatures continuing with a chance of snow tomorrow.\",\"currentConditions\":{\"temp\":\"30.7\",\"sunrise\":\"07:26:24\",\"sunset\":\"17:15:52\",\"feelslike\":\"25.2\",\"humidity\":\"87.8\",\"conditions\":\"Overcast\"},\"days\":[{\"datetime\":\"2025-02-13\",\"temp\":\"29.4\",\"tempmax\":\"31.0\",\"tempmin\":\"28.3\",\"conditions\":\"Snow, Overcast\",\"description\":\"Cloudy skies throughout the day with a chance of snow throughout the day.\"},{\"datetime\":\"2025-02-14\",\"temp\":\"31.3\",\"tempmax\":\"35.0\",\"tempmin\":\"29.0\",\"conditions\":\"Snow, Rain, Partially cloudy\",\"description\":\"Partly cloudy throughout the day with early morning snow or rain.\"},{\"datetime\":\"2025-02-15\",\"temp\":\"30.1\",\"tempmax\":\"32.8\",\"tempmin\":\"27.6\",\"conditions\":\"Partially cloudy\",\"description\":\"Partly cloudy throughout the day.\"},{\"datetime\":\"2025-02-16\",\"temp\":\"28.6\",\"tempmax\":\"31.5\",\"tempmin\":\"26.1\",\"conditions\":\"Partially cloudy\",\"description\":\"Clearing in the afternoon.\"},{\"datetime\":\"2025-02-17\",\"temp\":\"26.4\",\"tempmax\":\"30.6\",\"tempmin\":\"22.0\",\"conditions\":\"Clear\",\"description\":\"Clear conditions throughout the day.\"},{\"datetime\":\"2025-02-18\",\"temp\":\"27.5\",\"tempmax\":\"32.3\",\"tempmin\":\"23.4\",\"conditions\":\"Clear\",\"description\":\"Clear conditions throughout the day.\"},{\"datetime\":\"2025-02-19\",\"temp\":\"29.8\",\"tempmax\":\"35.0\",\"tempmin\":\"25.4\",\"conditions\":\"Clear\",\"description\":\"Clear conditions throughout the day.\"},{\"datetime\":\"2025-02-20\",\"temp\":\"31.9\",\"tempmax\":\"37.5\",\"tempmin\":\"29.4\",\"conditions\":\"Partially cloudy\",\"description\":\"Partly cloudy throughout the day.\"},{\"datetime\":\"2025-02-21\",\"temp\":\"31.8\",\"tempmax\":\"37.7\",\"tempmin\":\"27.9\",\"conditions\":\"Partially cloudy\",\"description\":\"Becoming cloudy in the afternoon.\"},{\"datetime\":\"2025-02-22\",\"temp\":\"29.9\",\"tempmax\":\"35.0\",\"tempmin\":\"26.3\",\"conditions\":\"Clear\",\"description\":\"Clear conditions throughout the day.\"},{\"datetime\":\"2025-02-23\",\"temp\":\"28.7\",\"tempmax\":\"34.1\",\"tempmin\":\"24.0\",\"conditions\":\"Clear\",\"description\":\"Clear conditions throughout the day.\"},{\"datetime\":\"2025-02-24\",\"temp\":\"30.5\",\"tempmax\":\"37.1\",\"tempmin\":\"24.2\",\"conditions\":\"Partially cloudy\",\"description\":\"Becoming cloudy in the afternoon.\"},{\"datetime\":\"2025-02-25\",\"temp\":\"33.9\",\"tempmax\":\"39.3\",\"tempmin\":\"29.9\",\"conditions\":\"Overcast\",\"description\":\"Cloudy skies throughout the day.\"},{\"datetime\":\"2025-02-26\",\"temp\":\"37.7\",\"tempmax\":\"42.2\",\"tempmin\":\"33.9\",\"conditions\":\"Overcast\",\"description\":\"Cloudy skies throughout the day.\"},{\"datetime\":\"2025-02-27\",\"temp\":\"42.2\",\"tempmax\":\"49.7\",\"tempmin\":\"36.8\",\"conditions\":\"Overcast\",\"description\":\"Cloudy skies throughout the day.\"}]}";
  
      ObjectMapper mapper = new ObjectMapper();
      london = mapper.readValue(jsonLondon, CityInfo.class);
      berlin = mapper.readValue(jsonBerlin, CityInfo.class);
    }catch (Exception e) {
      e.printStackTrace();
    }

    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testcompareDaylightHours(){

  }

}