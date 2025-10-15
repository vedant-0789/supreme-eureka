package com.dsl.simulator.config;

import com.dsl.simulator.Service.GroundStationService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final GroundStationService groundStationService;

    @Override
    public void run(String... args) {
        groundStationService.initializeDefaultGroundStations();
    }
}
