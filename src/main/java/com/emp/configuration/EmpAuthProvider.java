//package com.emp.configuration;
//import com.emp.employee.service.CustomEmployeeDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//@Component
//@Service
//public class EmpAuthProvider implements AuthenticationProvider {
//
//    @Autowired
//    private CustomEmployeeDetailsService employeeDetailsService;
//
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//
//        String username = authentication.getName();
//
//        String password = (String) authentication.getCredentials();
//
//        UserDetails employee = employeeDetailsService.loadUserByUsername(username);
//
//        if (passwordEncoder.matches(password, employee.getPassword())) {
//            return new UsernamePasswordAuthenticationToken(username, password, employee.getAuthorities());
//        }
////        if (employee!=null) {
////            return new UsernamePasswordAuthenticationToken(username, password, employee.getAuthorities());
////        }
//
//        throw new BadCredentialsException("🙁");
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return UsernamePasswordAuthenticationToken.class.equals(aClass);
//    }
//}
