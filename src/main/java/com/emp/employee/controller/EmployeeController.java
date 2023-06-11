package com.emp.employee.controller;
//import com.emp.configuration.EmpAuthProvider;

//import com.emp.configuration.EmpAuthProvider;
import com.emp.employee.dto.RegisterEmployee;
import com.emp.employee.dto.UpdateEmloyeeDetails;
import com.emp.employee.model.Employee;
import com.emp.employee.service.EmployeeService;
import com.emp.exceptions.GlobalExceptions;

import com.emp.user.Dto.LoginRequest;
import com.emp.utils.BCrypt;
import com.emp.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

//    @Autowired
//    private EmployeeService employeeService;

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        super();
        this.employeeService= employeeService;

    }
//    @Autowired
//    @Qualifier("empAuth")
//   private AuthenticationManager manager;

//    @Autowired
//    private EmpAuthProvider manager;
    @Autowired
    private JwtUtils jwtUtils;

   @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    @PostMapping("/regEmployee")
    public ResponseEntity<Employee> registerEmployee(@Valid @RequestBody RegisterEmployee registerEmployee) {
        Employee employee = employeeService.save(registerEmployee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }


//    @PostMapping("/login")
//    public ResponseEntity<Map<String,String>>loginEmployee(@Valid @RequestBody LoginRequest loginRequest){
//        System.out.println("hereeee.......");
//        Authentication authentication= manager.authenticate(new UsernamePasswordAuthenticationToken(
//                loginRequest.getUsername(), loginRequest.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        Employee employee= employeeService.findEmployeeByUsername(loginRequest.getUsername());
//        String jwt = jwtUtils.generateTokenForEmployee(employee);
//
//        return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));



//        Employee employee= employeeService.findEmployeeByUserNameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
//        if (employee!=null){
//            String token = jwtUtils.generateTokenForEmployee(employee);
//            Map responseMap = new HashMap<>();
//            responseMap.put("token", token);
//            return new ResponseEntity<>(responseMap,HttpStatus.OK);
//        }else {
//            return new ResponseEntity<>(Map.of(),HttpStatus.BAD_REQUEST);
//        }
//    }
//    @PostMapping("/login")
//    public ResponseEntity<JWTLoginSuccessResponse>loginEmployee(@Valid @RequestBody LoginRequest loginRequest){
//
//        Authentication authentication= manager.authenticate(new UsernamePasswordAuthenticationToken(
//                loginRequest.getUsername(), loginRequest.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        Employee employee= employeeService.findEmployeeByUsername(loginRequest.getUsername());
//        String jwt = jwtUtils.generateTokenForEmployee(employee);
//
//        return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));
//}
@PostMapping("/login")
public ResponseEntity<Map<String,String>> userLogin(@Valid @RequestBody LoginRequest loginRequest){
//        String encodedPassword=bCryptPasswordEncoder.encode(loginRequest.getPassword());
//        System.out.println(encodedPassword);
//    String hashedPassword = BCrypt.hashpw(loginRequest.getPassword(), BCrypt.gensalt());
    Employee employee = employeeService.findEmployeeByUsername(loginRequest.getUsername());
    if(employee != null){
        if (!BCrypt.checkpw(loginRequest.getPassword(),employee.getPassword())){
            throw new GlobalExceptions("Incorrect password");
        }else {
            String token=jwtUtils.generateTokenForEmployee(employee);
            Map responseMap = new HashMap<>();
            responseMap.put("token",token);
            return new ResponseEntity<>(responseMap,HttpStatus.OK);
        }
        }
        else{
        return new ResponseEntity<>(Map.of(),HttpStatus.BAD_REQUEST);
    }
}

    @GetMapping("/employee")
    public ResponseEntity<Employee>findEmployeeByFirstName(@RequestParam String firstName  ){
        Employee employee = employeeService.findByFirstName(firstName);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id")String id ,@RequestBody UpdateEmloyeeDetails updateEmloyeeDetails) {
       employeeService.updateEmployeeDetail(updateEmloyeeDetails, id);
        return new ResponseEntity<>("Updated employee successfully" , HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> showAllEmployee(){
        List<Employee> employees= employeeService.findAll();
        return new ResponseEntity<>(employees,HttpStatus.OK);
    }
}
