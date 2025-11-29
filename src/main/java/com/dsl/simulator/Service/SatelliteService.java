package com.dsl.simulator.Service;

import com.dsl.simulator.Entity.Satellite;
import com.dsl.simulator.Repository.SatelliteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SatelliteService {

    private final SatelliteRepository satelliteRepository;

    @Autowired
    public SatelliteService(SatelliteRepository satelliteRepository) {
        this.satelliteRepository = satelliteRepository;
    }

    public Satellite addSatellite(Satellite satellite) {
        return satelliteRepository.save(satellite);
    }

    public List<Satellite> getAllSatellites() {
        return satelliteRepository.findAll();
    }

    public Optional<Satellite> getSatelliteByNoradId(Integer noradId) {
        return satelliteRepository.findByNoradId(noradId);
    }
}
