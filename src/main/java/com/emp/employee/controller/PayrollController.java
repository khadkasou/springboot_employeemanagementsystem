package com.emp.employee.controller;

import com.emp.employee.dto.PayrollDto;
import com.emp.employee.model.PayrollReport;
import com.emp.employee.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payroll")
public class PayrollController {

    @Autowired
    private PayrollService payrollService;


//    @GetMapping("/list")
//    public ResponseEntity<?>generatePayrollReport(@RequestParam("month") String month,
//                                                  @RequestParam("year") Integer year){
//        List<PayrollReport> payrollReports= payrollService.generatePayrollReport(month,year);
//        return new ResponseEntity<>(payrollReports, HttpStatus.OK);
//    }

    @PostMapping("/payroll-report")
    public ResponseEntity<?> generateReports(){
        return new ResponseEntity<>(payrollService.genereatePayroll(),HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER') ")
    @PostMapping("/save")
    public ResponseEntity<?> savePayrollReport(@RequestBody PayrollDto payrollDto){
        PayrollReport payrollReport= payrollService.save(payrollDto);
        return new ResponseEntity(payrollReport,HttpStatus.OK);
    }
}
