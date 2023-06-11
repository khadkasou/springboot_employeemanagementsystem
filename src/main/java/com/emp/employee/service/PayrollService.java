package com.emp.employee.service;

import com.emp.employee.dto.PayrollDto;
import com.emp.employee.model.PayrollReport;

import java.util.List;

public interface PayrollService {

    public List<PayrollReport> genereatePayroll();
//    public PayrollReport generatePayrollReportByEmployeeId(String employeeId);
    public PayrollReport save(PayrollDto payrollDto);
    public List<PayrollReport> findAll();
    public PayrollReport generatePayrollReportByUsername(String username);



}
