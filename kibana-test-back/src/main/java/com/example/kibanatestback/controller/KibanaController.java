package com.example.kibanatestback.controller;

import com.example.kibanatestback.service.KibanaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/kibana")
public class KibanaController {

    private final KibanaService kibanaService;

    public KibanaController(KibanaService kibanaService) {
        this.kibanaService = kibanaService;
    }

    @GetMapping("dashboard")
    public ResponseEntity<String> getDashboard() {
        return kibanaService.getDashboard();
    }

}
