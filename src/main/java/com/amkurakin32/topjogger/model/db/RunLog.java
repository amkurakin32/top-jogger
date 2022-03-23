package com.amkurakin32.topjogger.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "run_log")
public class RunLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedAt;
    private int distance;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "weather_log_id", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private WeatherLog weatherLog;
}