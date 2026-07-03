package com.pranay.notesapi.controller;

import com.pranay.notesapi.config.AppConfig;
import com.pranay.notesapi.dto.HealthResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HealthController {

    private final AppConfig appConfig;

    public HealthController(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @GetMapping("/health")
    public HealthResponse health() {

        return new HealthResponse(
                "UP",
                appConfig.getAppName(),
                appConfig.getAppVersion(),
                LocalDateTime.now().toString()
        );
    }
}