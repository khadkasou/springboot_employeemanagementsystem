package com.emp.user.controller;
import com.emp.exceptions.GlobalExceptions;
import com.emp.user.Dto.LoginRequest;
import com.emp.user.Dto.SignupRequest;
import com.emp.user.model.User;
import com.emp.user.service.UserService;
import com.emp.utils.BCrypt;
import com.emp.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

//    @Autowired
//    private UserService userService;

    private UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;

    }


    //    @Autowired
//    private AuthenticationManager manager;
//    @Autowired
//    private UserAuthProvider manager;
    @Autowired
    private JwtUtils jwtUtils;

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping("/saveUser")
    public ResponseEntity<?> userRegister(@RequestBody SignupRequest signupRequest) {
        User newUser = userService.registerUser(signupRequest);
        return new ResponseEntity<>("Saved", HttpStatus.OK);
    }

    @PostMapping("/userLogin")
    public ResponseEntity<Map<String, String>> userLogin(@RequestBody LoginRequest loginRequest, Model model) {

//        String encodedPassword=bCryptPasswordEncoder.encode(loginRequest.getPassword());
//        System.out.println(encodedPassword);
//        String hashedPassword = BCrypt.hashpw(loginRequest.getPassword(), BCrypt.gensalt());

        User user = userService.findUserByUserName(loginRequest.getUsername());

        if (user != null) {
            if (!BCrypt.checkpw(loginRequest.getPassword(), user.getPassword())) {

                throw new GlobalExceptions("Incorrect password");
            } else {
                String token = jwtUtils.generateToken(user);
                Map responseMap = new HashMap<>();
                responseMap.put("token", token);
                return new ResponseEntity<>(responseMap, HttpStatus.OK);
            }


        } else {
            return new ResponseEntity<>(Map.of(), HttpStatus.BAD_REQUEST);
        }

    }

//    @RequestMapping(value = "/username", method = RequestMethod.GET)
//    @ResponseBody
//    public String currentUserNameSimple(HttpServletRequest request) {
//        Principal principal = request.getUserPrincipal();
//        return principal.getName();
//    }

    //    @PostMapping("/login")
//    public ResponseEntity<JWTLoginSuccessResponse> login(@RequestBody LoginRequest loginRequest) {
//        Authentication authentication = manager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        User userDetails= userService.findUserByUsername(loginRequest.getUsername());
//        String jwt = jwtUtils.generateToken(userDetails);
//        return ResponseEntity.ok(new JWTLoginSuccessResponse(true,jwt));
//    }
    @GetMapping("/username")

    public ResponseEntity<?> currentUserName() {
        return new ResponseEntity<>(userService.findUserDetails(), HttpStatus.OK);
    }


}
