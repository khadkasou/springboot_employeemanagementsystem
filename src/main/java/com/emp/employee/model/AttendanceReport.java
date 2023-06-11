package com.emp.employee.model;

import lombok.Data;

@Data
public class AttendanceReport {
    private String employeeId;
    private int totalDaysPresent;
    private int totalDaysAbsent;

}
