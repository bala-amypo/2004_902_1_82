package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.EmployeeProfileDto;
import com.example.demo.service.EmployeeProfileService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeProfileController {

    @Autowired
    private EmployeeProfileService employeeProfileService;

    @PostMapping
    public EmployeeProfileDto create(@RequestBody EmployeeProfileDto dto) {
        return employeeProfileService.createProfile(dto);
    }

    @GetMapping("/{id}")
    public EmployeeProfileDto getById(@PathVariable Long id) {
        return employeeProfileService.getById(id);
    }

    @GetMapping
    public List<EmployeeProfileDto> getAll() {
        return employeeProfileService.getAll();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        employeeProfileService.delete(id);
        return "Employee deleted successfully";
    }
}