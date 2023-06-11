package com.emp.employee.controllerFrontEnd;

import com.emp.employee.dto.RegisterEmployee;
import com.emp.employee.dto.UpdateEmloyeeDetails;
import com.emp.employee.model.Employee;
import com.emp.employee.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping("/v2/employee")
public class EmployeeControllerFrontEnd {

    private final EmployeeService employeeService;

    @ModelAttribute("registerEmployee")
    public RegisterEmployee registerEmployee(){
        return new RegisterEmployee();
    }


    @GetMapping("/registerEmployee")
    public String employeeRegistration(Model model){
        RegisterEmployee registerEmployee= new RegisterEmployee();
        model.addAttribute("register", registerEmployee);
        return "employeeRegistration";
    }

    @PostMapping("/save-employee")
    public String employeeRegister(@ModelAttribute RegisterEmployee registerEmployee){
        Employee employee= employeeService.save(registerEmployee);

        return "admin";
    }

    @GetMapping("/list-employee")
    public String showEmployeeList(Model model){
        List<Employee> allEmployee = employeeService.findAll();
        model.addAttribute("employeeList", allEmployee);
        return "employeeList";
    }
    @GetMapping("/{id}")
    public String deleteEmployee(@PathVariable("id") String id) {
        employeeService.deleteEmployee(id);

        return "employeeList";
    }


    @GetMapping("/update/{id}")
    public String updateEmployee(@PathVariable("id") String id, Model model){
        Employee emp = employeeService.findById(id);
            model.addAttribute("employeeDetails", emp);
        return "updateEmployeeDetails";

    }

@PostMapping("/update-employee")
public String employeeUpdate(@ModelAttribute UpdateEmloyeeDetails updateEmloyeeDetails,
                              String id) {
    Employee employee = employeeService.updateEmployeeDetail(updateEmloyeeDetails, id);

    return "employeeList";
}








//    @GetMapping("/page/{pageNo}")
//    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
//                                @RequestParam("sortField") String sortField,
//                                @RequestParam("sortDir") String sortDir,
//                                Model model) {
//        int pageSize = 5;
//
//        Page<Employee> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
//        List<Employee> listEmployees = page.getContent();
//
//        model.addAttribute("currentPage", pageNo);
//        model.addAttribute("totalPages", page.getTotalPages());
//        model.addAttribute("totalItems", page.getTotalElements());
//
//        model.addAttribute("sortField", sortField);
//        model.addAttribute("sortDir", sortDir);
//        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
//
//        model.addAttribute("listEmployees", listEmployees);
//        return "index";
//    }
}
