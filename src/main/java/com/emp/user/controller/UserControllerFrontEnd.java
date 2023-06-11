package com.emp.user.controller;
import com.emp.user.Dto.SignupRequest;
import com.emp.user.model.User;
import com.emp.user.repository.UserRepo;
import com.emp.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
@AllArgsConstructor
@RequestMapping("/v1/user")
public class UserControllerFrontEnd {

    private final UserService userService;
    private final UserRepo userRepo;

    @GetMapping("/registration")
    public String userRegistration(Model model) {
        SignupRequest signupRequest = new SignupRequest();
        model.addAttribute("signup", signupRequest);
        return "userRegistration";
    }

    @ModelAttribute("signupRequest")
    public SignupRequest signupRequest() {
        return new SignupRequest();
    }

//    @GetMapping("/list-user")
//    public String showUserList(Model model) {
//        List<User> allUser = userService.getAllUser();
//        model.addAttribute("userList", allUser);
//        return "adminList";
//
//    }

    @PostMapping("/save-admin")
    public String userRegister(@ModelAttribute SignupRequest signupRequest) {
        User newUser = userService.registerUser(signupRequest);
//        session.setAttribute("msg", "New Admin added successfully...");

        return "userRegistration";
    }

    @GetMapping("/{id}")
    public String deleteUser(@PathVariable("id") String id, HttpSession session) {
        userRepo.deleteById(id);
        session.setAttribute("msg","User deleted successfully..");
        return "success";
    }



}
