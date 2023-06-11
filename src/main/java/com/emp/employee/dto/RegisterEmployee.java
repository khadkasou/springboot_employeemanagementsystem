package com.emp.employee.dto;

import com.emp.employee.model.Address;
import lombok.Data;

import java.util.List;


@Data
public class RegisterEmployee {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Integer age;
    private String username;
    private String password;
    private String role ;
    private String departments;
//    private String gender;
//    private List<Address> addresses;
}
