package com.sentinelx.app.controller;

import com.sentinelx.app.model.EventLog;
import com.sentinelx.app.repository.EventLogRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class AdminLogController {

    private final EventLogRepository logRepository;

    public AdminLogController(EventLogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @GetMapping
    public List<EventLog> getAllLogs() {
        return logRepository.findAll();
    }
}
