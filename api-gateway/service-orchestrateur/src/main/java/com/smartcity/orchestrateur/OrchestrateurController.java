package com.smartcity.orchestrateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OrchestrateurController {

    @Autowired
    private OrchestrationService service;

    @PostMapping("/planifier-trajet")
    public ResponseEntity<PlanificationResponse> planifierTrajet(
            @RequestBody PlanificationRequest request) {
        
        PlanificationResponse response = service.planifier(request);
        return ResponseEntity.ok(response);
    }
}