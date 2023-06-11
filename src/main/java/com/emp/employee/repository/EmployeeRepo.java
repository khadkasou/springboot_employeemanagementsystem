package com.emp.employee.repository;

import com.emp.employee.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends MongoRepository<Employee, String> {
    public Employee findByPhone(String phone);
    public Employee findByEmail(String email);
//    public Employee findByAge(Integer age);
//    public Employee findByFirstName(String firstName);
    public Optional<Employee> findById(String id);

    public Employee findEmployeeByUsername(String username);

    public Employee findByUsername(String username);



}
