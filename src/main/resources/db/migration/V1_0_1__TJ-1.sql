CREATE TABLE user
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    login    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    name     VARCHAR(255) NOT NULL,
    surname  VARCHAR(255) NOT NULL,
    role     TINYINT      NOT NULL
);
CREATE UNIQUE INDEX idx_user_login ON user (login);

CREATE TABLE weather_log
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    lat         DECIMAL(6, 4) NOT NULL,
    lon         DECIMAL(7, 4) NOT NULL,
    temperature DECIMAL(4, 2) NOT NULL,
    cloudiness  DECIMAL(2, 0) NOT NULL,
    wind_speed  DECIMAL(3, 1) NOT NULL,
    wind_deg    DECIMAL(3, 0) NOT NULL,
    wind_gust   DECIMAL(3, 1) NOT NULL,
    pressure    DECIMAL(4, 0),
    visibility  INT,
    clouds      DECIMAL(3, 0)
);
CREATE UNIQUE INDEX idx_weather_log_lat_lon ON weather_log (lat, lon);

CREATE TABLE run_log
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id         BIGINT    NOT NULL,
    added_at        TIMESTAMP NOT NULL,
    distance        INT       NOT NULL,
    weather_log_id BIGINT
);
ALTER TABLE run_log
    ADD CONSTRAINT fk_run_log_user_id FOREIGN KEY (user_id) REFERENCES user (id);
ALTER TABLE run_log
    ADD CONSTRAINT fk_run_log_weather_log_id FOREIGN KEY (weather_log_id) REFERENCES weather_log (id);