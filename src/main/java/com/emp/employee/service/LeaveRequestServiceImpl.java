package com.emp.employee.service;

import com.emp.employee.dto.LeaveRequestDto;
import com.emp.employee.model.LeaveRequest;
import com.emp.employee.model.LeaveStatus;
import com.emp.employee.repository.LeaveRequestRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LeaveRequestServiceImpl implements LeaveRequestService{


    private LeaveRequestRepo leaveRequestRepo;


    @Override
    public LeaveRequest findByUsername(String username) {
        return leaveRequestRepo.findByUsername(username);
    }

    @Override
    public List<LeaveRequest> findByStatus(String status) {
        return leaveRequestRepo.findByStatus(LeaveStatus.valueOf(status));
    }

    @Override
    public LeaveRequest updateLeaveRequest(String username, LeaveRequestDto leaveRequest) {
        LeaveRequest existingLeaveRequest = leaveRequestRepo.findById(username).get();
        if (existingLeaveRequest.equals(null)){
            throw  new RuntimeException("Leave request not found with Id "+leaveRequest.getUsername());
        }

        return leaveRequestRepo.save(existingLeaveRequest);
    }
    @Override
    public Optional<LeaveRequest> getleaveRequestByUsername(String username) {
        return leaveRequestRepo.findById(username);
    }

    @Override
    public List<LeaveRequest> findAll() {
        return leaveRequestRepo.findAll();
    }

    @Override
    public LeaveRequest createLeaveRequest(LeaveRequestDto leaveRequestDto) {

        LeaveRequest createLeaveRequest= new LeaveRequest();

        createLeaveRequest.setUsername(leaveRequestDto.getUsername());
        createLeaveRequest.setReason(leaveRequestDto.getReason());
        createLeaveRequest.setFromDate(leaveRequestDto.getFromDate());
        createLeaveRequest.setToDate(leaveRequestDto.getToDate());
        createLeaveRequest.setLeaveType(leaveRequestDto.getLeaveType());
        createLeaveRequest.setStatus(LeaveStatus.REQUESTED);

        return leaveRequestRepo.save(createLeaveRequest);
    }

//    @Override
//    public void rejectLeaveRequest(String employeeId) {
//        LeaveRequest leaveRequest= leaveRequestRepo.findById(employeeId).orElse(null);
//        if(leaveRequest!=null){
//            leaveRequest.setStatus(LeaveStatus.REJECTED);
//           leaveRequestRepo.save(leaveRequest);
//        }
//    }
//    @Override
//    public void approveLeaveRequest(String employeeId){
//        LeaveRequest leaveRequest= leaveRequestRepo.findById(employeeId).orElse(null);
//        if (leaveRequest!=null){
//            leaveRequest.setStatus(LeaveStatus.APPROVED);
//            leaveRequestRepo.save(leaveRequest);
//        }
//
//    }
}
