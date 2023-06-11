package com.emp.employee.controllerFrontEnd;
import com.emp.employee.dto.LeaveRequestDto;
import com.emp.employee.model.Employee;
import com.emp.employee.model.LeaveRequest;
import com.emp.employee.service.LeaveRequestService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/v2/employee")
public class LeaveRequestControllerFrontEnd {

private final LeaveRequestService leaveRequestService;


    @ModelAttribute("leaveRequest")
    public LeaveRequestDto leaveRequest() {
        return new LeaveRequestDto();
    }

@GetMapping("/leaveRequest")
    public String employeeLeave(Model model){
        LeaveRequestDto leaveRequest= new LeaveRequestDto();
        model.addAttribute("leaveRequest", leaveRequest);
        return "/aboutLeave";
}

@PostMapping("/create-leave-request")
    public String leaveRequest(@ModelAttribute LeaveRequestDto leaveRequestDto){
    LeaveRequest createLeaveRequest= leaveRequestService.createLeaveRequest(leaveRequestDto);
    return "/aboutLeave";
}


    @GetMapping("/list-Leave")
    public String showLeaveList(Model model){
        List<LeaveRequest> allRequest = leaveRequestService.findAll();
        model.addAttribute("requestList", allRequest);
        return "leaveRequestList";
    }
}
