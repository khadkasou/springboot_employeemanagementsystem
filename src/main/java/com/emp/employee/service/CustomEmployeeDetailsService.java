package com.emp.employee.service;

import com.emp.employee.model.Employee;
import com.emp.employee.repository.EmployeeRepo;
import com.emp.exceptions.GlobalExceptions;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Component
@Qualifier("empDetails")
@AllArgsConstructor
public class CustomEmployeeDetailsService implements UserDetailsService {
    private EmployeeRepo employeeRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Employee employee= employeeRepo.findEmployeeByUsername(username);

        if (employee==null) new GlobalExceptions("User does not exists.");
        return new org.springframework.security.core.userdetails.User(employee.getUsername(), employee.getPassword(),
                mapRoleToAuthority(employee.getRole()));
    }

private Collection<? extends GrantedAuthority> mapRoleToAuthority(String role) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(role));
    return authorities;
}
}
