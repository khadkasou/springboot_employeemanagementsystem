package com.emp.employee.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Data
@Document(collection = "attendance")
public class Attendance {

    @Id
    private String id;
//    private String employeeId;
    private String username;
    private AttendanceStatus status;
//    private String username;
//    private String password;
//    @JsonFormat(pattern = "mm/dd/yyyy")
    private LocalDate date;

}
