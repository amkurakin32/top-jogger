package com.amkurakin32.topjogger.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@ApiModel(description = "JSON to create new user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest extends CreateUserBaseRequest {

    @NotBlank(message = "User role is mandatory")
    @ApiModelProperty(dataType = "string", required = true, value = "User role. Possible values: user, manager, admin", example = "user")
    private String role;
}