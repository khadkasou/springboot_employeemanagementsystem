package com.emp.employee.controller;

import com.emp.employee.dto.LeaveRequestDto;
import com.emp.employee.model.LeaveRequest;
import com.emp.employee.model.LeaveStatus;
import com.emp.employee.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping("/api")
public class LeaveRequestController {

    @Autowired
    private LeaveRequestService leaveRequestService;


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/pending")
    public List<LeaveRequest> getPendingLeaveRequests() {
        return leaveRequestService.findByStatus(String.valueOf(LeaveStatus.REQUESTED));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/approve")
    public List<LeaveRequest> getApprovedLeaveRequests() {
        return leaveRequestService.findByStatus(String.valueOf(LeaveStatus.APPROVED));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/reject")
    public List<LeaveRequest> getRejectedLeaveRequests() {
        return leaveRequestService.findByStatus(String.valueOf(LeaveStatus.REJECTED));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{username}")
    public ResponseEntity<?> getLeaveRequestById(@PathVariable("username") String username){
        LeaveRequest leaveRequest= leaveRequestService.getleaveRequestByUsername(username).get();
        return new ResponseEntity<>(leaveRequest,HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    @PostMapping("/create")
    public ResponseEntity<?> createLeaveRequest(@RequestBody LeaveRequestDto leaveRequestDto){
        LeaveRequest createLeaveRequest= leaveRequestService.createLeaveRequest(leaveRequestDto);
        return new ResponseEntity<>(createLeaveRequest, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    @PutMapping("/update")
    public ResponseEntity<?> updateLeaveRequest(@PathVariable("employeeId") String id,
                                                 @RequestBody LeaveRequestDto leaveRequest){
        LeaveRequest updateLeaverequest= leaveRequestService.updateLeaveRequest(id ,leaveRequest);
        return new ResponseEntity<>(updateLeaverequest, HttpStatus.OK);
    }
//    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
//    @PostMapping("/approve/{employeeId}")
//    public ResponseEntity<?> approveLeaveRequest(@PathVariable("employeeId") String id){
//        leaveRequestService.approveLeaveRequest(id);
//        return new ResponseEntity<>("APPROVED",HttpStatus.OK);
//    }

//    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
//    @PostMapping("/reject/{employeeId}")
//    public ResponseEntity<?> rejectLeaveRequest(@PathVariable("employeeId") String id){
//             leaveRequestService.rejectLeaveRequest(id);
//        return new ResponseEntity<>("REJECTED", HttpStatus.OK);
//
//    }


//    @PostMapping("/reject/{employeeId}")
//    public
//    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
//    @PostMapping("/pending/{employeeId}")
//    private void pendingLeaveRequest(@PathVariable("employeeId") String employeeId){
//        leaveRequestService.pendingLeaveRequest(employeeId);
//    }
}
