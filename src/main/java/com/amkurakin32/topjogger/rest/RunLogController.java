package com.amkurakin32.topjogger.rest;

import com.amkurakin32.topjogger.model.request.RunLogCreateRequest;
import com.amkurakin32.topjogger.model.response.RunLogResponse;
import com.amkurakin32.topjogger.model.response.TopJoggerErrorResponse;
import com.amkurakin32.topjogger.service.RunLogService;
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
@RequestMapping(value = "/restapi/v1/run-log")
@Api(value = "/restapi/v1/run-log", tags = "Run logs")
public class RunLogController {

    private final RunLogService runLogService;

    @Autowired
    public RunLogController(RunLogService runLogService) {
        this.runLogService = runLogService;
    }

    @ApiOperation(value = "Returns single run log by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK - run log is found"),
            @ApiResponse(code = 404, message = "Run log is not found.", response = TopJoggerErrorResponse.class)})
    @GetMapping(value = "/{runLogId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RunLogResponse> getById(@PathVariable("runLogId") Long runLogId) {
        RunLogResponse runLogResponse = runLogService.findById(runLogId);
        return new ResponseEntity<>(runLogResponse, HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "OK - run log is found")})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RunLogResponse>> getAll() {
        List<RunLogResponse> runLogs = runLogService.findAll();
        return new ResponseEntity<>(runLogs, HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "OK - run log is found")})
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RunLogResponse> create(@RequestBody @Valid RunLogCreateRequest runLogCreateRequest) {
        RunLogResponse runLogResponse = runLogService.create(runLogCreateRequest);
        return new ResponseEntity<>(runLogResponse, HttpStatus.CREATED);
    }
}
