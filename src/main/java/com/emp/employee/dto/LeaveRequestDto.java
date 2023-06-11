package com.emp.employee.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LeaveRequestDto {

    private String username;
    private String reason;
    private String leaveType;
    private String fromDate;
    private String toDate;

}
