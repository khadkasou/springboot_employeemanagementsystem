package com.emp.employee.controllerFrontEnd;
import com.emp.employee.model.Employee;
import com.emp.employee.service.EmployeeService;
import com.emp.exceptions.GlobalExceptions;
import com.emp.user.Dto.LoginRequest;
import com.emp.user.model.User;
import com.emp.user.service.UserService;
import com.emp.utils.BCrypt;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@AllArgsConstructor
@RequestMapping("/")
public class Login {

    private final UserService userService;

    private final EmployeeService employeeService;

    @ModelAttribute("loginRequest")
    public LoginRequest loginRequest() {
        return new LoginRequest();
    }


    @GetMapping("/login")
    public String userLogin(Model model) {
        LoginRequest loginRequest = new LoginRequest();
        model.addAttribute("login", loginRequest);
        return "login";
    }

    @PostMapping("/login-user")
    public String loggedinUser(@ModelAttribute LoginRequest loginRequest) {
        User user = userService.findUserByUserName(loginRequest.getUsername());
        if (user != null) {
            if (!BCrypt.checkpw(loginRequest.getPassword(), user.getPassword())) {

                throw new GlobalExceptions("Incorrect password");
            } else {
                return "/admin";
            }

        } else {
            Employee employee = employeeService.findEmployeeByUsername(loginRequest.getUsername());
            if (employee != null) {
                if (!BCrypt.checkpw(loginRequest.getPassword(), employee.getPassword())) {
                    throw new GlobalExceptions("Incorrect password");
                } else {
                    return "/employee";
                }
            }
        }
        throw new GlobalExceptions("Invalid Username or Password");

    }

}
