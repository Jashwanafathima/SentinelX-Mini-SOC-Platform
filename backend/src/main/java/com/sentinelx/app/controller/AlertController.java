package com.sentinelx.app.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@CrossOrigin(origins = "http://localhost:3000") // allow React frontend
@RestController
@RequestMapping("/api")
public class AlertController {

    @GetMapping("/alerts")
    public List<Map<String, String>> getAlerts() {
        // sample alerts
        List<Map<String, String>> alerts = new ArrayList<>();

        Map<String, String> alert1 = new HashMap<>();
        alert1.put("id", "1");
        alert1.put("type", "Malware");
        alert1.put("severity", "High");
        alert1.put("time", "2026-02-15 10:00");
        alerts.add(alert1);

        Map<String, String> alert2 = new HashMap<>();
        alert2.put("id", "2");
        alert2.put("type", "Phishing");
        alert2.put("severity", "Medium");
        alert2.put("time", "2026-02-15 11:00");
        alerts.add(alert2);

        return alerts;
    }
}
