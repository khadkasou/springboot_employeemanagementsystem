package com.emp.utils;

import com.emp.user.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoggedInUserServiceImpl implements LoggedInUserService{


    public User getLoggedinUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null){
            Object principle=authentication.getPrincipal();
            if(principle instanceof User){
                User user = (User) principle;
               return user;

            }else{

            }
        }
        return null;
    }

}
