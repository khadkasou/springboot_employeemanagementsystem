package com.emp.employee.dto;

import com.emp.employee.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProfileDto {
    private String firstName;
    private String lastName;
    private int age;
    private String phone;
    private String role;
    private String department;
    private List<Address> addresses;
    private Date dob;
}
