package com.glvov.aitexttoolsbackend.core.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/health")
@CrossOrigin(origins = "*")
public class HealthController {

    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity<Void> healthCheck() {
        return ResponseEntity.ok()
                .header("X-App-Status", "Healthy")
                .build();
    }
}