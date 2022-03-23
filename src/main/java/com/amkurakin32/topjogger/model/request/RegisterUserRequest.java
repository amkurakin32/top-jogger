package com.amkurakin32.topjogger.model.request;


import com.amkurakin32.topjogger.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.NoArgsConstructor;

@ApiModel(description = "JSON to register new user")
@NoArgsConstructor
public class RegisterUserRequest extends CreateUserBaseRequest {

    @Override
    @JsonIgnore
    public String getRole(){
        return Role.USER.getName();
    }
}
