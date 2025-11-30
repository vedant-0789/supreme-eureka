package com.dsl.simulator.controller;

import com.dsl.simulator.Service.PingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PingController {
private final PingService pingService;
public PingController(PingService pingService) {
    this.pingService = pingService;
}
@GetMapping("/ping")
    public String ping() {
    pingService.ping();
        return "Hello Ping";
    }
}
