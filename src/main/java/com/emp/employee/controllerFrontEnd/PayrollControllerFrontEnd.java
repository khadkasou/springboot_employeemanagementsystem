package com.emp.employee.controllerFrontEnd;

import com.emp.employee.dto.PayrollDto;
import com.emp.employee.model.PayrollReport;
import com.emp.employee.service.PayrollService;
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
public class PayrollControllerFrontEnd {

    private final PayrollService payrollService;

    @ModelAttribute("payroll")
    public PayrollDto payroll(){
        return new PayrollDto();
    }

    @GetMapping("/payroll")
    public String employeePayroll(Model model){
        PayrollDto payroll= new PayrollDto();
        model.addAttribute("empPayroll",payroll);
        return "payroll";
    }

    @PostMapping("/save-payroll")
    public String employeePayroll(@ModelAttribute PayrollDto payrollDto){
        PayrollReport payrollReport= payrollService.save(payrollDto);

        return "payroll";
    }

    @GetMapping("/list-payroll")
    public String showPayrollList(Model model){
        List<PayrollReport> payrollList = payrollService.findAll();
        model.addAttribute("empPayrollList", payrollList);
        return "payrollList";
    }
}
