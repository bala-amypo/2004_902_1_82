package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(
    name = "productivity_metrics",
    uniqueConstraints = @UniqueConstraint(columnNames = {"employee_id", "metric_date"})
)
public class ProductivityMetricRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int hoursWorked;
    private int tasksCompleted;
    private int meetingsAttended;
    private int productivityScore;

    @Column(name = "metric_date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeProfile employee;

    public ProductivityMetricRecord() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getHoursWorked() { return hoursWorked; }
    public void setHoursWorked(int hoursWorked) { this.hoursWorked = hoursWorked; }

    public int getTasksCompleted() { return tasksCompleted; }
    public void setTasksCompleted(int tasksCompleted) { this.tasksCompleted = tasksCompleted; }

    public int getMeetingsAttended() { return meetingsAttended; }
    public void setMeetingsAttended(int meetingsAttended) { this.meetingsAttended = meetingsAttended; }

    public int getProductivityScore() { return productivityScore; }
    public void setProductivityScore(int productivityScore) { this.productivityScore = productivityScore; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public EmployeeProfile getEmployee() { return employee; }
    public void setEmployee(EmployeeProfile employee) { this.employee = employee; }
}
