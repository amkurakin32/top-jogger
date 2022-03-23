package com.amkurakin32.topjogger.rest;

import com.amkurakin32.topjogger.model.db.User;
import com.amkurakin32.topjogger.model.request.LogInRequest;
import com.amkurakin32.topjogger.model.request.RegisterUserRequest;
import com.amkurakin32.topjogger.model.response.AuthenticationResponse;
import com.amkurakin32.topjogger.model.response.TopJoggerErrorResponse;
import com.amkurakin32.topjogger.service.JwtService;
import com.amkurakin32.topjogger.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/restapi/v1/auth")
@Api(value = "/restapi/v1/auth", tags = "Authentication")
public class AuthController {

    private final UserService userService;

    private final JwtService jwtService;

    public AuthController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @ApiOperation(value = "Authenticates a user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Invalid login/password pair", response = TopJoggerErrorResponse.class)})
    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> doLogin(@RequestBody @Valid LogInRequest logInRequest) {
        User user = userService.findByLoginAndVerifyPassword(logInRequest.getLogin(), logInRequest.getPassword());
        String jwtToken = jwtService.generateToken(user.getLogin());
        return new ResponseEntity<>(new AuthenticationResponse(jwtToken), HttpStatus.OK);
    }

    @ApiOperation(value = "Registers new user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request body", response = TopJoggerErrorResponse.class)})
    @PostMapping(path = "/register")
    public void register(@RequestBody @Valid RegisterUserRequest registerUserRequest) {
        userService.create(registerUserRequest);
    }
}
