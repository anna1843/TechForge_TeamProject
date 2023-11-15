package org.spring.dev.company.entity.weather;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "weather")
public class WeatherEntity {
    @Id
    @Column(name = "weather_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lat;

    private String lon;

    private String country;

    private double temp;

    private double temp_max;

    private double temp_min;

}

