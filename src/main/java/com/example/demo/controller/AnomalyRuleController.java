package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.AnomalyRuleDto;
import com.example.demo.service.AnomalyRuleService;

@RestController
@RequestMapping("/api/anomaly-rules")
public class AnomalyRuleController {

    @Autowired
    private AnomalyRuleService anomalyRuleService;

    @PostMapping
    public AnomalyRuleDto createRule(@RequestBody AnomalyRuleDto dto) {
        return anomalyRuleService.createRule(dto);
    }

    @GetMapping
    public List<AnomalyRuleDto> getAllRules() {
        return anomalyRuleService.getAllRules();
    }

    @PutMapping("/{id}/activate")
    public String activate(@PathVariable Long id) {
        anomalyRuleService.activateRule(id);
        return "Rule activated";
    }
}
