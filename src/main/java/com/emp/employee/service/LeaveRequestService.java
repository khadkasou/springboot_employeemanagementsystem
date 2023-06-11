package com.emp.employee.service;

import com.emp.employee.dto.LeaveRequestDto;
import com.emp.employee.model.LeaveRequest;

import java.util.List;
import java.util.Optional;

public interface LeaveRequestService {

//    public void approveLeaveRequest(String employeeId);
//    public void rejectLeaveRequest(String employeeId);
    public LeaveRequest findByUsername(String username);
    public List<LeaveRequest> findByStatus(String status);
    LeaveRequest updateLeaveRequest(String username, LeaveRequestDto leaveRequest);
//
   Optional<LeaveRequest> getleaveRequestByUsername(String username);
   List<LeaveRequest> findAll();
   LeaveRequest createLeaveRequest(LeaveRequestDto leaveRequestDto);

}
