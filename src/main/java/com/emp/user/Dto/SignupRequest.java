package com.emp.user.Dto;
import lombok.Data;
@Data
public class SignupRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phone;
    private Integer age;
    private String password;
    private String confirmPassword;
//    private String gender;
    private String role;

}
