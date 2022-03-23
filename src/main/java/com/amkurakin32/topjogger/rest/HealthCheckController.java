package com.amkurakin32.topjogger.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/restapi/v1/")
@Api(value = "/restapi/v1/", tags = "HealthCheck")
public class HealthCheckController {

    @ApiOperation(value = "Returns Health of Service: HTTP code = 200, '0' in body of response")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK - if service-check passed"),
            @ApiResponse(code = 500, message = "Health check failed.") })
    @RequestMapping(value = "/health", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> health() {
        return new ResponseEntity<>("0", HttpStatus.OK);
    }
}