package com.dsl.simulator.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ground_stations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroundStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "station_id", unique = true)
    private String stationId;  // ✅ For frontend deploy endpoint

    @Column(unique = true, nullable = false)
    private String code;  // e.g., "KSC", "ESA"

    @Column(nullable = false)
    private String name;  // e.g., "NASA Kennedy Space Center"

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    private String country;

    @Column(name = "min_elevation")
    private Double minElevation = 5.0;  // ✅ Minimum elevation angle for visibility (degrees)

    @Column(name = "max_range")
    private Double maxRange = 2000.0;  // Maximum communication range (km)

    @Column(nullable = false)
    private Boolean active = true;
}
