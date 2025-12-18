

package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ProductivityMetricDto;
import com.example.demo.service.ProductivityMetricService;

@RestController
@RequestMapping("/api/productivity")
public class ProductivityMetricController {

    @Autowired
    private ProductivityMetricService productivityMetricService;

    @PostMapping
    public ProductivityMetricDto submitMetric(@RequestBody ProductivityMetricDto dto) {
        return productivityMetricService.submitMetric(dto);
    }

    @GetMapping("/employee/{employeeId}")
    public List<ProductivityMetricDto> getMetricsByEmployee(
            @PathVariable Long employeeId) {
        return productivityMetricService.getMetricsByEmployee(employeeId);
    }
}