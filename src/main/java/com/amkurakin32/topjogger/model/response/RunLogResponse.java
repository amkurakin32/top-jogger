package com.amkurakin32.topjogger.model.response;

import com.amkurakin32.topjogger.model.db.RunLog;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ApiModel(description = "JSON payload for run log response")
@AllArgsConstructor
@ToString
@Getter
@Setter
public class RunLogResponse {

    @ApiModelProperty(dataType = "long", value = "Run log id", example = "123")
    private long id;

    @ApiModelProperty(dataType = "date", value = "Date time of the run log creation (ISO 8601 format)", example = "2020-06-10T12:24:50.000+0000")
    private Date addedAt;

    @ApiModelProperty(dataType = "int", value = "Run distance in meters", example = "9123")
    private int distance;

    @ApiModelProperty(dataType = "float", value = "Location's latitude", example = "38.89")
    private double lat;

    @ApiModelProperty(dataType = "float", value = "Location's longitude", example = "-77.6")
    private double lon;

    @ApiModelProperty(dataType = "float", value = "Temperature in celsius", example = "23.3")
    private double temperature;

    @ApiModelProperty(dataType = "float", value = "Cloudiness in percents", example = "75")
    private float cloudiness;

    @ApiModelProperty(dataType = "float", value = "Wind speed in meter/sec", example = "4.1")
    private double windSpeed;

    @ApiModelProperty(dataType = "float", value = "Wind direction, degrees", example = "200")
    private float windDeg;

    @ApiModelProperty(dataType = "float", value = "Wind gust in meter/sec", example = "5.5")
    private double windGust;

    @ApiModelProperty(dataType = "int", value = " Atmospheric pressure hPa", example = "1027")
    private int pressure;

    @ApiModelProperty(dataType = "int", value = "Visibility in meters", example = "10000")
    private int visibility;

    public RunLogResponse(RunLog runLog) {
        this.id = runLog.getId();
        this.addedAt = runLog.getAddedAt();
        this.distance = runLog.getDistance();
        this.lat = runLog.getWeatherLog().getLat();
        this.lon = runLog.getWeatherLog().getLon();
        this.temperature = runLog.getWeatherLog().getTemperature();
        this.cloudiness = runLog.getWeatherLog().getCloudiness();
        this.windSpeed = runLog.getWeatherLog().getWindSpeed();
        this.windDeg = runLog.getWeatherLog().getWindDeg();
        this.windGust = runLog.getWeatherLog().getWindGust();
        this.pressure = runLog.getWeatherLog().getPressure();
        this.visibility = runLog.getWeatherLog().getVisibility();
    }
}
