package com.amkurakin32.topjogger.model.response.openweather;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentWeatherResponse {

    @JsonProperty("visibility")
    private int visibility;

    @JsonProperty("timezone")
    private int timezone;

    @JsonProperty("main")
    private Main main;

    @JsonProperty("clouds")
    private Clouds clouds;

    @JsonProperty("sys")
    private Sys sys;

    @JsonProperty("dt")
    private int dt;

    @JsonProperty("coord")
    private Coord coord;

    @JsonProperty("weather")
    private List<WeatherItem> weather;

    @JsonProperty("name")
    private String name;

    @JsonProperty("cod")
    private int cod;

    @JsonProperty("id")
    private int id;

    @JsonProperty("base")
    private String base;

    @JsonProperty("wind")
    private Wind wind;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Coord {

        private double lon;

        private double lat;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Main {


        private double temp;

        @JsonProperty("temp_min")
        private double tempMin;

        private int humidity;

        private int pressure;

        @JsonProperty("feels_like")
        private double feelsLike;

        @JsonProperty("temp_max")
        private double tempMax;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Sys {

        private String country;

        private int sunrise;

        private int sunset;

        private int id;

        private int type;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WeatherItem {


        private String icon;

        private String description;

        private String main;

        private int id;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Wind {

        private int deg;

        private double speed;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Clouds {

        private int all;
    }
}