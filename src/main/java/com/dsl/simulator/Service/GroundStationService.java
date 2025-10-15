package com.dsl.simulator.Service;

import com.dsl.simulator.Repository.GroundStationRepository;
import com.dsl.simulator.Repository.SatelliteRepository;
import com.dsl.simulator.dto.CommunicationLinkDTO;
import com.dsl.simulator.Entity.GroundStation;
import com.dsl.simulator.Entity.Satellite;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.me.g4dpz.satellite.LEOSatellite;
import uk.me.g4dpz.satellite.SatPos;
import uk.me.g4dpz.satellite.TLE;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroundStationService {

    private final GroundStationRepository groundStationRepository;
    private final SatelliteRepository satelliteRepository;

    // Initialize default ground stations
    public void initializeDefaultGroundStations() {
        if (groundStationRepository.count() == 0) {
            List<GroundStation> stations = List.of(
                    GroundStation.builder()
                            .stationId("gs_ksc")  // ✅ ADD THIS
                            .code("KSC")
                            .name("NASA Kennedy Space Center")
                            .latitude(28.5729)
                            .longitude(-80.6490)
                            .country("USA")
                            .active(true)
                            .build(),
                    GroundStation.builder()
                            .stationId("gs_esa")  // ✅ ADD THIS
                            .code("ESA")
                            .name("ESA Ground Station Madrid")
                            .latitude(40.4530)
                            .longitude(-4.3679)
                            .country("Spain")
                            .active(true)
                            .build(),
                    GroundStation.builder()
                            .stationId("gs_isro")  // ✅ ADD THIS
                            .code("ISRO")
                            .name("ISRO Bangalore")
                            .latitude(12.9716)
                            .longitude(77.5946)
                            .country("India")
                            .active(true)
                            .build(),
                    GroundStation.builder()
                            .stationId("gs_jaxa")  // ✅ ADD THIS
                            .code("JAXA")
                            .name("JAXA Tsukuba")
                            .latitude(36.0833)
                            .longitude(140.0833)
                            .country("Japan")
                            .active(true)
                            .build(),
                    GroundStation.builder()
                            .stationId("gs_ros")  // ✅ ADD THIS
                            .code("ROS")
                            .name("Roscosmos Moscow")
                            .latitude(55.7558)
                            .longitude(37.6173)
                            .country("Russia")
                            .active(true)
                            .build()
            );

            groundStationRepository.saveAll(stations);
            log.info("✅ Initialized {} ground stations", stations.size());
        }
    }


    // Get all active ground stations
    public List<GroundStation> getAllActiveStations() {
        return groundStationRepository.findByActiveTrue();
    }

    // Calculate all communication links
    public List<CommunicationLinkDTO> calculateCommunicationLinks() {
        List<CommunicationLinkDTO> links = new ArrayList<>();
        List<GroundStation> stations = groundStationRepository.findByActiveTrue();
        List<Satellite> satellites = satelliteRepository.findAll();
        Date now = new Date();

        for (Satellite sat : satellites) {
            try {
                TLE tle = new TLE(new String[]{
                        sat.getName(),
                        sat.getTleLine1(),
                        sat.getTleLine2()
                });

// ✅ Alternative: Use LEOSatellite (works for most satellites)
                uk.me.g4dpz.satellite.Satellite satLib = new LEOSatellite(tle);
                for (GroundStation station : stations) {
                    CommunicationLinkDTO link = calculateLink(satLib, sat, station, now);
                    if (link.getIsVisible()) {
                        links.add(link);
                    }
                }
            } catch (Exception e) {
                log.error("Error calculating links for satellite {}: {}", sat.getSatelliteId(), e.getMessage());
            }
        }

        return links;
    }

    // Calculate single link between satellite and ground station
    private CommunicationLinkDTO calculateLink(
            uk.me.g4dpz.satellite.Satellite satLib,
            Satellite sat,
            GroundStation station,
            Date time
    ) {
        try {
            uk.me.g4dpz.satellite.GroundStationPosition gsPos =
                    new uk.me.g4dpz.satellite.GroundStationPosition(
                            station.getLatitude(),
                            station.getLongitude(),
                            0.0  // Altitude in meters
                    );

            SatPos satPos = satLib.getPosition(gsPos, time);

            double elevation = Math.toDegrees(satPos.getElevation());
            double azimuth = Math.toDegrees(satPos.getAzimuth());
            double range = satPos.getRange();  // Range in km

            boolean isVisible = elevation >= station.getMinElevation();

            String quality;
            String color;
            if (elevation > 60) {
                quality = "excellent";
                color = "#00ff00";  // Green
            } else if (elevation > 30) {
                quality = "good";
                color = "#ffff00";  // Yellow
            } else if (elevation > 15) {
                quality = "fair";
                color = "#ffa500";  // Orange
            } else {
                quality = "poor";
                color = "#ff0000";  // Red
            }

            return CommunicationLinkDTO.builder()
                    .satelliteId(sat.getSatelliteId())
                    .satelliteName(sat.getName())
                    .groundStationCode(station.getCode())
                    .groundStationName(station.getName())
                    .isVisible(isVisible)
                    .elevationAngle(elevation)
                    .azimuthAngle(azimuth)
                    .distance(range)
                    .signalQuality(quality)
                    .linkColor(color)
                    .build();

        } catch (Exception e) {
            log.error("Error calculating link: {}", e.getMessage());
            return CommunicationLinkDTO.builder()
                    .satelliteId(sat.getSatelliteId())
                    .isVisible(false)
                    .build();
        }
    }
}
