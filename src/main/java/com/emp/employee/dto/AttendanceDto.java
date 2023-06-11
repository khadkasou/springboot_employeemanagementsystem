package com.emp.employee.dto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Data
@Getter
@Setter
public class AttendanceDto {

//    private String employeeId;
    private String username;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;


//    private String username;
//    private String password;
}
