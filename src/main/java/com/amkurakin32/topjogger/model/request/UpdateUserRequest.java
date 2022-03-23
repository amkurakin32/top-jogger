package com.amkurakin32.topjogger.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@ApiModel(description = "JSON to update existing user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

    @ApiModelProperty(dataType = "string", required = true, value = "User password", example = "pAs$w0rd")
    private String password;

    @NotBlank(message = "User name is mandatory")
    @ApiModelProperty(dataType = "string", required = true, value = "User name", example = "Alexey")
    private String name;

    @NotBlank(message = "User surname is mandatory")
    @ApiModelProperty(dataType = "string", required = true, value = "User surname", example = "Kurakin")
    private String surname;

    @NotBlank(message = "User role is mandatory")
    @ApiModelProperty(dataType = "string", required = true, value = "User role. Possible values: user, manager, admin", example = "user")
    private String role;
}
