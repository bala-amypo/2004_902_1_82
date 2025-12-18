
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