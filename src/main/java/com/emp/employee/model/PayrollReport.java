package com.emp.employee.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "payroll_report")
public class PayrollReport {
    @Id
    private String id;
    private String username;
    private LocalDate date;
//    private LocalDate year;

   // private Double perDaySalary;
    private Double grossSalary;
    private Double tax;
    private Double netSalary;

}
