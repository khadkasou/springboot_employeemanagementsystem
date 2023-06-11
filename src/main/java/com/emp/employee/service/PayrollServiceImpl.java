package com.emp.employee.service;
import com.emp.employee.dto.PayrollDto;
import com.emp.employee.model.Employee;
import com.emp.employee.model.PayrollReport;
import com.emp.employee.repository.PayrollRepo;
import com.emp.exceptions.GlobalExceptions;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class PayrollServiceImpl implements PayrollService{

    private PayrollRepo payrollRepo;
    private EmployeeService employeeService;

    @Override
    public List<PayrollReport> genereatePayroll() {
        List<PayrollReport> payrollReports = payrollRepo.findAll();

        List<PayrollReport> reports= new ArrayList<>();
        for (PayrollReport payroll: payrollReports){
            reports.add(commonMethod(payroll));
        }
        return reports;
    }


    @Override
    public PayrollReport save(PayrollDto payrollDto) {

        PayrollReport payroll = new PayrollReport();
        payroll.setUsername(payrollDto.getUsername());
        payroll.setDate(payrollDto.getDate());
        Employee employeeDb= employeeService.findEmployeeByUsername(payroll.getUsername());
        if (employeeDb==null){
            throw new GlobalExceptions("Employee with given username already available...");
        }
        payroll.setGrossSalary(payrollDto.getGrossSalary());

        payroll = commonMethod(payroll);

        return payrollRepo.save(payroll);
    }

    @Override
    public List<PayrollReport> findAll() {
       return payrollRepo.findAll();
    }
    @Override
    public PayrollReport generatePayrollReportByUsername(String username) {
        PayrollReport payrollReport= payrollRepo.findByUsername(username);
        return commonMethod(payrollReport);
    }

    private PayrollReport commonMethod(PayrollReport payroll){
        Double grossSalary = payroll.getGrossSalary();
        Double tax = 0.00;
        if (grossSalary != null && grossSalary <= 25000) {
            tax= 0.00;
        } else if (grossSalary != null && grossSalary >= 25000 && grossSalary <= 50000) {
            tax= grossSalary* 0.1;
        } else if (grossSalary != null && grossSalary >= 50000 && grossSalary <= 100000) {
            tax= grossSalary*0.2;
        } else if (grossSalary != null && grossSalary >= 100000 && grossSalary <= 150000) {
            tax= grossSalary*0.3;
        } else if (grossSalary >= 150000) {
            tax=grossSalary*0.36;
        }
        Double netSalary = grossSalary - tax;

        payroll.setTax(tax);
        payroll.setNetSalary(netSalary);

        return payroll;
    }

    }

