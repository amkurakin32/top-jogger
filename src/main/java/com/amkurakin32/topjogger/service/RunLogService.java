package com.amkurakin32.topjogger.service;

import com.amkurakin32.topjogger.client.OpenWeatherMapClient;
import com.amkurakin32.topjogger.model.db.RunLog;
import com.amkurakin32.topjogger.model.db.User;
import com.amkurakin32.topjogger.model.db.WeatherLog;
import com.amkurakin32.topjogger.model.request.RunLogCreateRequest;
import com.amkurakin32.topjogger.model.response.RunLogResponse;
import com.amkurakin32.topjogger.model.response.openweather.HistoryWeatherResponse;
import com.amkurakin32.topjogger.repository.RunLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RunLogService implements IRunLogService {

  private final AuthService authService;

  private final RunLogRepository runLogRepository;

  private final OpenWeatherMapClient openWeatherMapClient;

  @Autowired
  public RunLogService(AuthService authService, RunLogRepository runLogRepository,
                       OpenWeatherMapClient openWeatherMapClient) {
    this.authService = authService;
    this.runLogRepository = runLogRepository;
    this.openWeatherMapClient = openWeatherMapClient;
  }

  public RunLogResponse findById(Long runLogId) {
    NoSuchElementException exception = new NoSuchElementException("Can't find run log by id: " + runLogId);
    RunLog runLog = runLogRepository.findById(runLogId).orElseThrow(() -> exception);
    User user = authService.getCurrentAuthenticatedUser();
    if (runLog.getUserId() != user.getId()) {
      log.warn("User with userId {} tries to access to foreign run log with id {}", user.getId(), runLogId);
      throw exception;
    }
    return new RunLogResponse(runLog);
  }

  public List<RunLogResponse> findAll() {
    User user = authService.getCurrentAuthenticatedUser();
    return runLogRepository.findAllByUserId(user.getId()).stream()
            .map(RunLogResponse::new)
            .collect(Collectors.toList());
  }

  @Transactional
  public RunLogResponse create(RunLogCreateRequest runLogCreateRequest) {
    User user = authService.getCurrentAuthenticatedUser();
    RunLog runLog = new RunLog();
    runLog.setUserId(user.getId());
    runLog.setDistance(runLogCreateRequest.getDistance());
    runLog.setAddedAt(runLogCreateRequest.getAddedAt());
    HistoryWeatherResponse historyWeatherResponse = openWeatherMapClient.getCurrentWeather(
            runLogCreateRequest.getLat(), runLogCreateRequest.getLon(), runLogCreateRequest.getAddedAt());
    runLog.setWeatherLog(new WeatherLog(historyWeatherResponse));
    RunLog createdRunLog = runLogRepository.save(runLog);
    return new RunLogResponse(createdRunLog);
  }
}
