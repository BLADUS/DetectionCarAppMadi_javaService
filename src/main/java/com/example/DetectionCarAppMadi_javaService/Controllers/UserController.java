package com.example.DetectionCarAppMadi_javaService.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.DetectionCarAppMadi_javaService.Entity.User;
import com.example.DetectionCarAppMadi_javaService.Services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("newUser") User newUser) {
        return "registrationPage";
    }

    @PostMapping("/registration")
    public String saveNewUser(@ModelAttribute("newUser") User newUser) {
        userService.saveUser(newUser);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(@ModelAttribute("user") User user) {
        return "loginPage";
    }

    @PostMapping("/login")
    public String authorization(@ModelAttribute("user") User user, HttpSession session) {
        User findUser = userService.findByUsername(user.getUsername());

        if (userService.verifyPassword(user.getPassword(), findUser.getPassword())) {
            session.setAttribute("currentUser", findUser);
            return "redirect:/home";
        } else {
            return "loginPage";
        }
    }

    @GetMapping("/home")
    public String homePage(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        model.addAttribute("currentUser", currentUser);
        return "homePage";
    }
}

