


package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.AnomalyFlagDto;
import com.example.demo.service.AnomalyFlagService;

@RestController
@RequestMapping("/api/anomalies")
public class AnomalyFlagController {

    @Autowired
    private AnomalyFlagService anomalyFlagService;

    @GetMapping
    public List<AnomalyFlagDto> getAllFlags() {
        return anomalyFlagService.getAllFlags();
    }

    @GetMapping("/employee/{employeeId}")
    public List<AnomalyFlagDto> getByEmployee(
            @PathVariable Long employeeId) {
        return anomalyFlagService.getByEmployee(employeeId);
    }
}