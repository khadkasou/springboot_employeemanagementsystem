package com.emp.employee.controllerFrontEnd;
import com.emp.employee.dto.AttendanceDto;
import com.emp.employee.model.Attendance;
import com.emp.employee.service.EmpAttendanceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/v2/employee")
public class EmpAttendanceControllerFrontEnd {

    private final EmpAttendanceService attendanceService;



    @ModelAttribute("attendance")
    public AttendanceDto attendance() {
        return new AttendanceDto();
    }


    @GetMapping("/attendance")
    public String employeeAttendance(Model model) {
        AttendanceDto attendance = new AttendanceDto();
        model.addAttribute("attendance", attendance);
        return "attendance";
    }


    @PostMapping("/save-attendance")
    public String empAttendance(@ModelAttribute AttendanceDto attendanceDto){
        Attendance attendance= attendanceService.save(attendanceDto);

        return "/employee";
    }

    @GetMapping("/attendanceReport")
    public String attendanceReport(Model model){
        AttendanceDto attendanceReport= new AttendanceDto();
        model.addAttribute("attendanceReport", attendanceReport);
        return "attendanceList";
    }



//    @PostMapping("/show-attendance-report")
//    public String employeeAttendanceReport( @RequestParam("startDate")
//                                                LocalDate startDate,
//                                            @RequestParam("endDate")
//                                                LocalDate endDate){
//        List<AttendanceReport> attendance= attendanceService.generateAttendanceReport(startDate,endDate);
//
//        return "/attendanceReport";
//
//
//    }

    @GetMapping("/show-attendance-list")
    public String employeeAttendanceReport(Model model){
        List<Attendance> allEmployee = attendanceService.findAll();
        model.addAttribute("attendanceList",allEmployee);
        return "attendanceList";
    }


}
