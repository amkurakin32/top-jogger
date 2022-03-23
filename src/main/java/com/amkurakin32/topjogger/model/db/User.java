package com.amkurakin32.topjogger.model.db;

import com.amkurakin32.topjogger.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(dataType = "long", value = "User id", example = "123")
    private long id;

    @ApiModelProperty(dataType = "string", value = "User login", example = "buddy123")
    private String login;

    @JsonIgnore
    private String password;

    @ApiModelProperty(dataType = "string", value = "User name", example = "Alexey")
    private String name;

    @ApiModelProperty(dataType = "string", value = "User surname", example = "Kurakin")
    private String surname;

    @JsonIgnore
    private int role;

    @JsonProperty("role")
    @ApiModelProperty(dataType = "string", value = "User role. Possible values: user, manager, admin", example = "user")
    public String getRoleStr() {
        return Role.getById(role).getName();
    }
}