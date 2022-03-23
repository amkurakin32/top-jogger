package com.amkurakin32.topjogger.client;

import com.amkurakin32.topjogger.model.response.openweather.HistoryWeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Date;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class OpenWeatherMapClient {

    private final String openWeatherHost;
    private final String openWeatherToken;
    private final WebClient webClient;

    private static final String historyWeatherPath = "/data/2.5/onecall/timemachine?lat={lat}&lon={lon}&dt={dt}&appid={appId}&units=metric";

    @Autowired
    public OpenWeatherMapClient(@Value("${open.weather.host}") String openWeatherHost, @Value("${open.weather.token}") String openWeatherToken,
                                WebClient webClient) {
        this.openWeatherHost = openWeatherHost;
        this.openWeatherToken = openWeatherToken;
        this.webClient = webClient;
    }

    public HistoryWeatherResponse getCurrentWeather(float lat, float lon, Date date) {
        ResponseEntity<HistoryWeatherResponse> response = webClient.get()
                .uri(openWeatherHost + historyWeatherPath, lat, lon, date.getTime(), openWeatherToken)
                .accept(APPLICATION_JSON)
                .retrieve()
                .toEntity(HistoryWeatherResponse.class)
                .block();
        return response.getBody();
    }
}
