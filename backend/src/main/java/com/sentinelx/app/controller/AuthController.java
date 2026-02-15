package com.sentinelx.app.controller;

import com.sentinelx.app.model.*;
import com.sentinelx.app.repository.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private EventLogRepository logRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestBody User request,
            HttpServletRequest httpRequest) {

        String ip = httpRequest.getRemoteAddr();

        Optional<User> user =
                userRepo.findByUsername(request.getUsername());

        Map<String, Object> response = new HashMap<>();

        if (user.isPresent() &&
                passwordEncoder.matches(
                        request.getPassword(),
                        user.get().getPassword())) {

            saveLog(request.getUsername(), ip,
                    "LOGIN_SUCCESS", "SUCCESS");

            response.put("success", true);
            response.put("message", "Login Success");

            return ResponseEntity.ok(response);
        }

        saveLog(request.getUsername(), ip,
                "LOGIN_FAILED", "FAILED");

        response.put("success", false);
        response.put("message", "Invalid Credentials");

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }

    private void saveLog(String username,
                         String ip,
                         String event,
                         String status) {

        EventLog log = new EventLog();
        log.setUsername(username);
        log.setIpAddress(ip);
        log.setEventType(event);
        log.setStatus(status);
        log.setTimestamp(LocalDateTime.now());

        logRepo.save(log);
    }
}
