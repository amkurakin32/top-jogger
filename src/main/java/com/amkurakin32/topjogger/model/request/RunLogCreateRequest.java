package com.amkurakin32.topjogger.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@ApiModel(description = "JSON to create new run log")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RunLogCreateRequest {

    @NotBlank(message = "Date time of the run log is mandatory")
    @ApiModelProperty(dataType = "date", required = true, value = "Date time of the run log creation (ISO 8601 format)", example = "2022-06-10T12:24:50.000+0000")
    private Date addedAt;

    @NotBlank(message = "Run distance is mandatory")
    @ApiModelProperty(dataType = "int", required = true, value = "Run distance in meters", example = "9123")
    private int distance;

    @NotBlank(message = "Latitude is mandatory")
    @ApiModelProperty(dataType = "float", required = true, value = "Location's latitude", example = "38.89")
    private float lat;

    @NotBlank(message = "Longitude is mandatory")
    @ApiModelProperty(dataType = "float", required = true, value = "Location's longitude", example = "-77.6")
    private float lon;
}