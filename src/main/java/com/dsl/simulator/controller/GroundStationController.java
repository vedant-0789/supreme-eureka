package com.dsl.simulator.controller;

import com.dsl.simulator.Entity.GroundStation;
import com.dsl.simulator.Service.GroundStationService;
import com.dsl.simulator.dto.CommunicationLinkDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GroundStationController {

    private final GroundStationService groundStationService;

    @Autowired
    public GroundStationController(GroundStationService groundStationService) {
        this.groundStationService = groundStationService;
    }

    @PostMapping("/ground-stations/initialize")
    public ResponseEntity<String> initializeGroundStations() {
        groundStationService.initializeDefaultGroundStations();
        return ResponseEntity.ok("Default ground stations initialized successfully.");
    }

    @GetMapping("/ground-stations")
    public ResponseEntity<List<GroundStation>> getActiveGroundStations() {
        List<GroundStation> activeStations = groundStationService.getAllActiveStations();
        return ResponseEntity.ok(activeStations);
    }

    @GetMapping("/communication-links")
    public ResponseEntity<List<CommunicationLinkDTO>> getCommunicationLinks() {
        List<CommunicationLinkDTO> links = groundStationService.calculateCommunicationLinks();
        return ResponseEntity.ok(links);
    }
}
