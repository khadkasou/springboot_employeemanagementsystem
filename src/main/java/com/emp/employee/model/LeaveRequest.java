package com.emp.employee.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Data
@Document(collection = "leave_requests")
public class LeaveRequest {
    @Id
    private String id;
    private String username;
    private String reason;
    private LeaveStatus status;
    private String leaveType;
    private String fromDate;
    private String toDate;


}
