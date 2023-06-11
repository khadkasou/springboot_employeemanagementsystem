package com.emp.employee.service;

import com.emp.employee.dto.RegisterEmployee;
import com.emp.employee.dto.UpdateEmloyeeDetails;
import com.emp.employee.model.Employee;
import com.emp.employee.repository.EmployeeRepo;
import com.emp.exceptions.GlobalExceptions;
import com.emp.utils.BCrypt;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{


    private EmployeeRepo employeeRepo;
    private MongoTemplate mongoTemplate;



    @Override
    public Employee save(RegisterEmployee employee) {

        Employee emp = new Employee();

        emp.setFirstName(employee.getFirstName());
        emp.setLastName(employee.getLastName());
//        emp.setGender(employee.getGender());
        emp.setAge(employee.getAge());
        Employee empEmail = employeeRepo.findByEmail(employee.getEmail());
        if(!Objects.isNull(empEmail)){
            throw  new GlobalExceptions("Employee with email "+employee.getEmail()+" already exists.");
        }
        emp.setEmail(employee.getEmail());
        Employee empPhone = employeeRepo.findByPhone(employee.getPhone());
        if (!Objects.isNull(empPhone)){
            throw new GlobalExceptions("Employee with phone "+employee.getPhone()+" already exists");
        }
        emp.setPhone(employee.getPhone());
        emp.setUsername(employee.getUsername());
        String hashedPassword = BCrypt.hashpw(employee.getPassword(), BCrypt.gensalt());
        emp.setPassword(hashedPassword);
        emp.setRole(employee.getRole());
//        emp.setAddresses(employee.getAddresses());
        emp.setDepartments(employee.getDepartments());


        return employeeRepo.save(emp);

    }

    @Override
    public Employee findByPhone(String phone) {
        return employeeRepo.findByPhone(phone);
    }

    @Override
    public Employee findByEmail(String email) {
        return employeeRepo.findByEmail(email);
    }


    @Override
    public Employee updateEmployeeDetail(UpdateEmloyeeDetails updateEmloyeeDetails, String id) {
        Optional<Employee> employee = employeeRepo.findById(id);

        if (!employee.isPresent()) {
            throw new GlobalExceptions("Employee with ID " + id + " not found.");
        }

        Employee emp = employee.get();
        emp.setFirstName(updateEmloyeeDetails.getFirstName());
        emp.setLastName(updateEmloyeeDetails.getLastName());
        emp.setPhone(updateEmloyeeDetails.getPhone());
        emp.setEmail(updateEmloyeeDetails.getEmail());
        emp.setUsername(updateEmloyeeDetails.getUsername());
        emp.setAge(updateEmloyeeDetails.getAge());
        emp.setDepartments(updateEmloyeeDetails.getDepartments());

        return employeeRepo.save(emp);
    }



    @Override
    public List<Employee> findAll() {

        return employeeRepo.findAll();
    }

    @Override
    public void deleteEmployee(String id) {
        employeeRepo.deleteById(id);
    }

    @Override
    public Employee findByFirstName(String firstName) {

//        return employeeRepo.findByFirstName(firstName);
        return null;
    }

    @Override
    public Employee findByAge(Integer age) {
        return null;
    }

    @Override
    public Employee findById(String id) {
        Optional<Employee> emp = employeeRepo.findById(id);
        Employee empl= null;
        if (emp.isPresent()){

            empl= emp.get();
        }else {
            throw new GlobalExceptions("Id doesn't found "+id);
        }
        return empl;
    }

    @Override
    public Employee findEmployeeByUsername(String username) {

       Query query = new Query(Criteria.where("username").is(username));

       return  mongoTemplate.findOne(query,Employee.class);
    }

    @Override
    public Employee findEmployeeByUserNameAndPassword(String userName, String password) {
        Query query= new Query(Criteria.where("username").is(userName).and("password").is(password));
        return mongoTemplate.findOne(query, Employee.class);

    }

    @Override
    public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        Query query = new Query().with(pageable);

        List<Employee> employees = mongoTemplate.find(query, Employee.class);
        long total = mongoTemplate.count(query, Employee.class);

        return new PageImpl<>(employees, pageable, total);


    }

//    @Override
//    public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
//        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
//                Sort.by(sortField).descending();
//
//        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
//        return this.employeeRepo.findAll(pageable);
//    }

//    Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
//            Sort.by(sortField).descending();
//
//    Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
//    Query query = new Query().with(pageable);
//
//    List<Employee> employees = mongoTemplate.find(query, Employee.class);
//    long total = mongoTemplate.count(query, Employee.class);
//
//return new PageImpl<>(employees, pageable, total);


//    @Override
//   public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
//
//        @Override
////    public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {

}
