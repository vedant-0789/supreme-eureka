package com.dsl.simulator.controller;

import com.dsl.simulator.Entity.Satellite;
import com.dsl.simulator.Service.SatelliteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SatelliteController {

    private final SatelliteService satelliteService;

    @Autowired
    public SatelliteController(SatelliteService satelliteService) {
        this.satelliteService = satelliteService;
    }

    @PostMapping("/satellites")
    public ResponseEntity<Satellite> addSatellite(@RequestBody Satellite satellite) {
        Satellite newSatellite = satelliteService.addSatellite(satellite);
        return ResponseEntity.ok(newSatellite);
    }

    @GetMapping("/satellites")
    public ResponseEntity<List<Satellite>> getAllSatellites() {
        List<Satellite> satellites = satelliteService.getAllSatellites();
        return ResponseEntity.ok(satellites);
    }

    @GetMapping("/satellites/norad/{noradId}")
    public ResponseEntity<Satellite> getSatelliteByNoradId(@PathVariable Integer noradId) {
        return satelliteService.getSatelliteByNoradId(noradId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/satellite-info/{noradId}")
    public ResponseEntity<Map<String, Object>> getSatelliteInfo(@PathVariable Integer noradId) {
        // Use a HashMap for more than 10 entries
        Map<String, Object> mockData = new HashMap<>();
        mockData.put("icon", "🛰️");
        mockData.put("name", "International Space Station");
        mockData.put("type", "Space Station");
        mockData.put("country", "International");
        mockData.put("orbitType", "LEO");
        mockData.put("altitudeKm", 400.0);
        mockData.put("velocity", 7.66);
        mockData.put("latitude", 0.0);
        mockData.put("longitude", 0.0);
        mockData.put("purpose", "Scientific Research");
        mockData.put("missionDescription", "A modular space station in low Earth orbit.");
        mockData.put("funFacts", List.of("The ISS is the largest human-made body in low Earth orbit.", "It travels at a speed of 17,500 mph."));
        
        return ResponseEntity.ok(mockData);
    }
}
