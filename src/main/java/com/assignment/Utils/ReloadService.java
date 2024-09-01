package com.assignment.Utils;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

/**
 * This service is used to prevent Render instances from spinning down due to inactivity. */
@Service
public class ReloadService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String URL = "https://assignment-y5o7.onrender.com/swagger-ui/index.html#/";

    @Scheduled(fixedRate = 30000) // 30 seconds
    public void reloadWebsite() {
        try {
            restTemplate.getForObject(URL, String.class);
            System.out.println("Reloaded at " + LocalDateTime.now());
        } catch (Exception e) {
            System.err.println("Error reloading at " + LocalDateTime.now() + ": " + e.getMessage());
        }
    }
}
