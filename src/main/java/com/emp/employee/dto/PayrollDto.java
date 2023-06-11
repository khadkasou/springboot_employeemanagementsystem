package com.emp.employee.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class PayrollDto {
    private String username;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private LocalDate year;
    private Double grossSalary;

}
