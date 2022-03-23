package com.amkurakin32.topjogger.model.response.openweather;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryWeatherResponse {

    private Current current;

    private String timezone;

    @JsonProperty("timezone_offset")
    private int timezoneOffset;

    private double lon;

    private double lat;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Current {

        private Rain rain;

        private int sunrise;

        private double temp;

        private int visibility;

        private double uvi;

        private int pressure;

        private int clouds;

        @JsonProperty("feels_like")
        private double feelsLike;

        private int dt;

        @JsonProperty("wind_deg")
        private int windDeg;

        @JsonProperty("dew_point")
        private double dewPoint;

        private int sunset;

        private List<WeatherItem> weather;

        private int humidity;

        @JsonProperty("wind_speed")
        private double windSpeed;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WeatherItem {

        private int id;

        private String main;

        private String description;

        private String icon;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Rain {

        @JsonProperty("1h")
        private double jsonMember1h;
    }
}