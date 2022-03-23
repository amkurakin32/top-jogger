package com.amkurakin32.topjogger.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@ApiModel(description = "JSON to create new user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogInRequest {
    @NotBlank(message = "User login is mandatory")
    @ApiModelProperty(dataType = "string", required = true, value = "User login", example = "buddy123")
    private String login;

    @NotBlank(message = "User password is mandatory")
    @ApiModelProperty(dataType = "string", required = true, value = "User password", example = "pAs$w0rd")
    private String password;
}
