package com.emp.employee.dto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Getter
@Setter
public class UpdateEmloyeeDetails {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Integer age;
    private String username;
//    private String password;

    private String departments;

//    private List<Address> addresses = new ArrayList<>();

}
