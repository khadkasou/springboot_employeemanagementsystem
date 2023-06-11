package com.emp.user.service;
import com.emp.exceptions.GlobalExceptions;
import com.emp.user.model.User;
import com.emp.user.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        User user = userRepo.findByUsername(username);


        if (user==null) new GlobalExceptions("User does not exists.");
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                mapRoleToAuthority(user.getRole()));

    }

//    private Collection<? extends GrantedAuthority> mapRolesToAuthority(User user){
//        List<GrantedAuthority> authorities =user.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//        return authorities;
//    }
//private Collection<? extends GrantedAuthority> mapRolesToAuthority(User user) {
//    List<GrantedAuthority> authorities = user.getRole().
//    authorities.add(new SimpleGrantedAuthority(role));
//    return authorities;
//}
        private Collection<? extends GrantedAuthority> mapRoleToAuthority(String role) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(role));
            return authorities;
        }


    }


