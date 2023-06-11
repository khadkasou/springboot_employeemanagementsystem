package com.emp.employee.service;

import com.emp.employee.dto.RegisterEmployee;
import com.emp.employee.dto.UpdateEmloyeeDetails;
import com.emp.employee.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    public Employee save(RegisterEmployee employee);
    public Employee findByPhone(String phone);
    public Employee findByEmail(String email);
    Employee updateEmployeeDetail(UpdateEmloyeeDetails updateEmloyeeDetails ,String id);
    public List<Employee> findAll();
    public void deleteEmployee(String id);
    public Employee findByFirstName(String firstName);
    public Employee findByAge(Integer age);
    public Employee findById(String id);
    public Employee findEmployeeByUsername(String username);

    public Employee findEmployeeByUserNameAndPassword(String userName, String password);

    Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
