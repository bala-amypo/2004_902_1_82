package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.service.UserAccountService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserAccountService userAccountService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        userAccountService.register(request);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return userAccountService.login(request);
    }
}



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

package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.TeamSummaryDto;
import com.example.demo.service.TeamSummaryService;

@RestController
@RequestMapping("/api/team-summary")
public class TeamSummaryController {

    @Autowired
    private TeamSummaryService teamSummaryService;

    @GetMapping
    public List<TeamSummaryDto> getAllSummaries() {
        return teamSummaryService.getAllSummaries();
    }

    @GetMapping("/{teamName}")
    public TeamSummaryDto getByTeam(@PathVariable String teamName) {
        return teamSummaryService.getByTeam(teamName);
    }
}