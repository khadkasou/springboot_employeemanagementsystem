package com.emp.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PasswordChange {

    private String username;
    private String currentPassword;
    private String newPassword;

}
