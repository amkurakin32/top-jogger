package com.amkurakin32.topjogger.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ApiModel(description = "JSON payload for successful authentication response")
@AllArgsConstructor
@ToString
@Getter
@Setter
public class AuthenticationResponse {
    @ApiModelProperty(dataType = "string", required = true, value = "jwtToken", example = "dshmwekf1srjbnejuingejtvnuorwfiwfejbvemfw")
    private String jwtToken;
}
