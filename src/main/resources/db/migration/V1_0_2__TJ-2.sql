ALTER TABLE weather_log DROP COLUMN clouds;
ALTER TABLE weather_log MODIFY COLUMN cloudiness DECIMAL(3, 0) NOT NULL;