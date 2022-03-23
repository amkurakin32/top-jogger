package com.amkurakin32.topjogger.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class CreateUserBaseRequest {
    @NotBlank(message = "User login is mandatory")
    @ApiModelProperty(dataType = "string", required = true, value = "User login", example = "buddy123")
    private String login;

    @NotBlank(message = "User password is mandatory")
    @ApiModelProperty(dataType = "string", required = true, value = "User password", example = "pAs$w0rd")
    private String password;

    @NotBlank(message = "User name is mandatory")
    @ApiModelProperty(dataType = "string", required = true, value = "User name", example = "Alexey")
    private String name;

    @NotBlank(message = "User surname is mandatory")
    @ApiModelProperty(dataType = "string", required = true, value = "User surname", example = "Kurakin")
    private String surname;

    public abstract String getRole();
}
