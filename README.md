# MyWeather App Tech Test

## Features

### 1. **Daylight Hours Comparison**
This feature allows users to compare the length of daylight hours (the time between sunrise and sunset) between two cities and return which city has the longest daylight.

#### How It Works:
- **Endpoint**: `GET /daylight/{city1}/{city2}`
- Example: `GET /daylight/London/Paris`
- The system retrieves the weather data for both cities, calculates the daylight hours by subtracting the sunrise time from the sunset time, and compares the results.
- Response: Returns a message stating which city has the longest daylight hours.

#### Example Response:
```json
"Paris has the longest daylight hours."
```

### 2. **Rain Check**
This feature checks if it is currently raining in either of the two cities provided by the user.

#### How It Works:
- **Endpoint**: `GET /rainfall/{city1}/{city2}`
- Example: `GET /rainfall/London/Paris`
- The system retrieves the current weather conditions for both cities and checks if the condition includes "Rain."
- Response: Returns a message indicating if it is raining in one, both or none of the cities.

#### Example Response:
```json
"Paris has the longest daylight hours."
```

## Function Descriptions

### WeatherController
- **compareDaylightHours**:  
  This method takes two city names and compares the daylight hours between them. It calls `WeatherService.compareDaylightHours()` to perform the comparison and return a message showing which city has the longest daylight.

- **rainCheck**:  
  This method takes two city names and checks if it is raining in either of the cities. It uses `WeatherService.rainCheck()` to see the current weather conditions and returns a message stating if it is raining in one or both cities.

### WeatherService
- **getDaylightHours**:  
  Calculates the daylight hours for a given city based on the sunrise and sunset times.

- **compareDaylightHours**:  
  Compares the daylight hours of two cities and returns a message showing which city has the longest daylight hours.

- **isRaining**:  
  Checks if it is raining in a given city by checking the weather conditions.

- **rainCheck**:  
  Compares the rain status between two cities and returns a message showing where it is raining (if at all).


### Setup Instructions
- Clone the repository.
- Install dependencies by running mvn install.
- Run the application using mvn spring-boot:run.



