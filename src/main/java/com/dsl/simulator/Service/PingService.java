package com.dsl.simulator.Service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PingService {
    public void ping() {
        System.out.println("Ping received at " + LocalDateTime.now());
    }
}
