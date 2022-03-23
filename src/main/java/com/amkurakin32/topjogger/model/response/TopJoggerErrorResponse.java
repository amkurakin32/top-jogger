package com.amkurakin32.topjogger.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ApiModel(description = "JSON payload for error response")
@AllArgsConstructor
@ToString
@Getter
@Setter
public class TopJoggerErrorResponse {

    @ApiModelProperty(dataType = "string", required = true, value = "error message", example = "Request failed")
    private String message;

    @ApiModelProperty(dataType = "string", required = true, value = "error code", example = "java.util.NoSuchElementException")
    private String errorCode;
}