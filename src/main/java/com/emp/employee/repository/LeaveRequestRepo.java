package com.emp.employee.repository;

import com.emp.employee.model.LeaveRequest;
import com.emp.employee.model.LeaveStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRequestRepo extends MongoRepository<LeaveRequest, String> {


//    public LeaveRequest findByEmployeeId(String employeeId);

    // public LeaveRequest findLeaveRequestByEmployeeId(LeaveRequest leaveRequest, String employeeId);
    List<LeaveRequest> findByStatus(LeaveStatus status);

     LeaveRequest findByUsername(String username);
}
