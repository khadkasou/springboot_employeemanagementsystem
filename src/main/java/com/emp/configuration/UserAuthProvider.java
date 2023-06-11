//package com.emp.configuration;
//
//import com.emp.user.service.CustomUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserAuthProvider implements AuthenticationProvider {
//
//   @Autowired
//   private CustomUserDetailsService userDetailsService;
//
////    @Autowired
////    private BCryptPasswordEncoder passwordEncoder;
//
////    @Autowired
////    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//
//        String username = authentication.getName();
//
//        String password = (String) authentication.getCredentials();
//
//        UserDetails user = userDetailsService.loadUserByUsername(username);
//
//        if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
//            return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
//        }
////        if (user!=null) {
////            return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
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
