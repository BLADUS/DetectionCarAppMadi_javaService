package com.example.DetectionCarAppMadi_javaService.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.DetectionCarAppMadi_javaService.Entity.User;
import com.example.DetectionCarAppMadi_javaService.Services.UserService;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("newUser") User newUser){
        return "registrationPage";
    }

    @PostMapping("/registration")
    public String saveNewUser(@ModelAttribute("newUser")User newUser){
        userService.saveUser(newUser);
        return "redirect:/upload";
    }

}
