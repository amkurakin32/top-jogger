package com.amkurakin32.topjogger.model.db;

import com.amkurakin32.topjogger.model.response.openweather.HistoryWeatherResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "weather_log")
public class WeatherLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)  private long id;
  private double lat;
  private double lon;
  private double temperature;
  private float cloudiness;
  private double windSpeed;
  private float windDeg;
  private float windGust;
  private int pressure;
  private int visibility;

  public WeatherLog(HistoryWeatherResponse historyWeatherResponse) {
    this.lat = historyWeatherResponse.getLat();
    this.lon = historyWeatherResponse.getLon();
    this.temperature = historyWeatherResponse.getCurrent().getTemp();
    this.cloudiness = historyWeatherResponse.getCurrent().getClouds();
    this.windSpeed = historyWeatherResponse.getCurrent().getWindSpeed();
    this.windDeg = historyWeatherResponse.getCurrent().getWindDeg();
    this.windGust = 0;
    this.pressure = historyWeatherResponse.getCurrent().getPressure();
    this.visibility = historyWeatherResponse.getCurrent().getVisibility();
  }
}