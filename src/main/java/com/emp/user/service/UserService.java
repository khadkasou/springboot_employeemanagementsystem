package com.emp.user.service;

import com.emp.user.Dto.SignupRequest;
import com.emp.user.model.User;
import org.apache.catalina.LifecycleState;

import java.awt.*;
import java.util.List;

public interface UserService {

    public User registerUser(SignupRequest signupRequest);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    public User findUserByUsername(String username);
    User findUserDetails();

    public User findUserByUserNameAndPassword(String userName,String password);

    public User findUserByUserName(String username);
  public List<User> getAllUser();
//  public void deleteUser()

}



