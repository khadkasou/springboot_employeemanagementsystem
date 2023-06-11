package com.emp.user.repository;

import com.emp.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.xml.transform.sax.SAXResult;

@Repository
public interface UserRepo extends MongoRepository<User, String> {

    public User findByUsername(String username);
    public Boolean existsByUsername(String username);
    public Boolean existsByEmail(String email);
    public  User findByEmail(String email);

    public User findUserByUsername(String username);
//    public Employee findByPhone(String phone);
//    public Employee findByAge(Integer age);
//    public Employee findByFirstName(String firstName);
//
//    public Employee findEmployeeByUsername(String username);


//    public User findUserByUserName



}
