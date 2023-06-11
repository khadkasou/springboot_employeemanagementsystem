package com.emp.employee.controller;

import com.emp.employee.dto.AttendanceDto;
import com.emp.employee.model.Attendance;
import com.emp.employee.model.AttendanceReport;
import com.emp.employee.service.EmpAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmpAttendanceController {

    @Autowired
    private EmpAttendanceService empAttendanceService;
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    @GetMapping("/report")
    public ResponseEntity<List<AttendanceReport>>generateEmpAttendanceReport(
                                @RequestParam("startDate")
                                 LocalDate startDate,
                                @RequestParam("endDate")
                                 LocalDate endDate){

           List<AttendanceReport> attendance= empAttendanceService.generateAttendanceReport(startDate,endDate);
        return new ResponseEntity<>(attendance, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @PostMapping("/attendance")
    public ResponseEntity<?>saveEmployeeAttendance(@RequestBody AttendanceDto attendanceDto){

        Attendance attendance= empAttendanceService.save(attendanceDto);
        return new ResponseEntity<>(attendance,HttpStatus.OK);
    }
}
