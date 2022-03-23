package com.amkurakin32.topjogger.rest;


import com.amkurakin32.topjogger.model.db.User;
import com.amkurakin32.topjogger.model.request.CreateUserRequest;
import com.amkurakin32.topjogger.model.request.UpdateUserRequest;
import com.amkurakin32.topjogger.model.response.TopJoggerErrorResponse;
import com.amkurakin32.topjogger.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/restapi/v1/users")
@Api(value = "/restapi/v1/users", tags = "Users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Returns single user found by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK - user is found"),
            @ApiResponse(code = 404, message = "User is not found.", response = TopJoggerErrorResponse.class)})
    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("userId") Long userId) {
        User user = userService.findById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ApiOperation(value = "Returns list of users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @ApiOperation(value = "Creates new user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request", response = TopJoggerErrorResponse.class)})
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        User createdUser = userService.create(createUserRequest);
        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }

    @ApiOperation(value = "Updates existing user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request", response = TopJoggerErrorResponse.class),
            @ApiResponse(code = 404, message = "User not found", response = TopJoggerErrorResponse.class)})
    @PutMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@PathVariable("userId") Long userId, @Valid @RequestBody UpdateUserRequest updateUserRequest) {
        User updatedUser = userService.updateUser(userId, updateUserRequest);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @ApiOperation(value = "Deletes a user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")})
    @DeleteMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteById(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}