package com.amkurakin32.topjogger.service;

import com.amkurakin32.topjogger.client.OpenWeatherMapClient;
import com.amkurakin32.topjogger.model.db.RunLog;
import com.amkurakin32.topjogger.model.db.User;
import com.amkurakin32.topjogger.model.db.WeatherLog;
import com.amkurakin32.topjogger.model.response.RunLogResponse;
import com.amkurakin32.topjogger.repository.RunLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RunLogServiceTest {


  @Mock
  AuthService authService;

  @Mock
  RunLogRepository runLogRepository;

  @Mock
  OpenWeatherMapClient openWeatherMapClient;

  RunLogService runLogService;

  @BeforeEach()
  public void init() {
    runLogService = new RunLogService(authService, runLogRepository, openWeatherMapClient);
  }


  @Test
  void findByIdTest() {
    Long runLogId = 42l;
    RunLog runLog = new RunLog();
    runLog.setUserId(11L);
    runLog.setAddedAt(new Date());
    runLog.setDistance(2000);
    runLog.setWeatherLog(new WeatherLog());
    when(runLogRepository.findById(eq(42l))).thenReturn(Optional.of(runLog));

    User user = new User();
    user.setId(11L);
    when(authService.getCurrentAuthenticatedUser()).thenReturn(user);

    RunLogResponse runLogResponse = runLogService.findById(runLogId);
    assertEquals(2000, runLogResponse.getDistance());
  }


  @Test
  void findByIdTest_LogNotFound() {
    Long runLogId = 42l;
    when(runLogRepository.findById(eq(42l))).thenReturn(Optional.empty());

    assertThrows(NoSuchElementException.class, () -> runLogService.findById(runLogId));
  }

  /* 00011100001001000
  fborder=false currGapSize = 3 maxGapSize = 4
  */


  @Test
  void findByIdTest_NotAuthorized() {
    Long runLogId = 42l;
    RunLog runLog = new RunLog();
    runLog.setUserId(11L);
    runLog.setAddedAt(new Date());
    runLog.setDistance(2000);
    runLog.setWeatherLog(new WeatherLog());
    when(runLogRepository.findById(any())).thenReturn(Optional.of(runLog));

    User user = new User();
    user.setId(22L);
    when(authService.getCurrentAuthenticatedUser()).thenReturn(user);

    assertThrows(NoSuchElementException.class, () -> runLogService.findById(runLogId));
    verify(authService, times(1)).getCurrentAuthenticatedUser();
  }

}