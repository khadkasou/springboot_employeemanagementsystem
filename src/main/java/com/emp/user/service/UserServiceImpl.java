package com.emp.user.service;

import com.emp.exceptions.GlobalExceptions;
import com.emp.user.Dto.SignupRequest;
import com.emp.user.model.User;
import com.emp.user.repository.UserRepo;
import com.emp.utils.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;


//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public User registerUser(SignupRequest signupRequest) {

        User user= new User();
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setAge(signupRequest.getAge());
//        user.setGender(signupRequest.getGender());
        user.setPhone(signupRequest.getPhone());
        if(this.existsByUsername(signupRequest.getUsername()))
            throw new GlobalExceptions("User with username "+signupRequest.getUsername()+" Already Exits.Try a new one");
        user.setUsername(signupRequest.getUsername());
        if (this.existsByEmail(signupRequest.getEmail()))
            throw new GlobalExceptions("User with email "+signupRequest.getEmail()+" Already exists.Try a new one");
       user.setEmail(signupRequest.getEmail());

        String hashedPassword = BCrypt.hashpw(signupRequest.getPassword(), BCrypt.gensalt(12));
        user.setPassword(hashedPassword);

        if (!signupRequest.getConfirmPassword().equals(signupRequest.getPassword())){
            throw new GlobalExceptions("Password you provide does not match.");
        }

//        List<Role> strole= signupRequest.getRoles();
//        List<Role> roles= new ArrayList<>();
//        for (Role role: strole){
//
//            if (roleRepo.findByName(role.getName())==null){
//                throw  new GlobalExceptions("Role with "+signupRequest.getRoles()+" not available");
//            }
//            roles.add(roleRepo.findByName(role.getName()));
//       }
//        user.setRoles(strole);

        user.setRole(signupRequest.getRole());

        return userRepo.save(user);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepo.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public User findUserDetails() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            User currentloggedInUserDetail = userRepo.findByUsername(currentUserName);
            return currentloggedInUserDetail;
        }
        return null;

    }


    @Override
    public User findUserByUserNameAndPassword(String userName,String password){

        Query query=new Query(Criteria.where("username").is(userName).and("password").is(password));

        return mongoTemplate.findOne(query,User.class);

    }

    @Override
    public User findUserByUserName(String username) {

        Query query= new Query(Criteria.where("username").is((username)));
        return mongoTemplate.findOne(query,User.class);
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }
}
