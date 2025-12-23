package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "anomaly_flags")
public class AnomalyFlagRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reason;
    private LocalDate flaggedDate;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeProfile employee;

    @ManyToOne
    @JoinColumn(name = "metric_id")
    private ProductivityMetricRecord metric;

    public AnomalyFlagRecord() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public LocalDate getFlaggedDate() { return flaggedDate; }
    public void setFlaggedDate(LocalDate flaggedDate) { this.flaggedDate = flaggedDate; }

    public EmployeeProfile getEmployee() { return employee; }
    public void setEmployee(EmployeeProfile employee) { this.employee = employee; }

    public ProductivityMetricRecord getMetric() { return metric; }
    public void setMetric(ProductivityMetricRecord metric) { this.metric = metric; }
}

