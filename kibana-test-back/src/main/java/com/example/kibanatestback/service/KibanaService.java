package com.example.kibanatestback.service;

import org.springframework.http.ResponseEntity;

public interface KibanaService {
    ResponseEntity<String> getDashboard();
}
