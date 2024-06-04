package com.example.DetectionCarAppMadi_javaService.Services;

import org.springframework.stereotype.Service;

import com.example.DetectionCarAppMadi_javaService.Entity.User;
import com.example.DetectionCarAppMadi_javaService.Repositories.UserRepository;
import com.example.DetectionCarAppMadi_javaService.Util.PasswordUtils;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordUtils passwordUtils;



    public UserService(UserRepository userRepository, PasswordUtils passwordUtils) {
        this.userRepository = userRepository;
        this.passwordUtils = passwordUtils;
    }



    public void saveUser(User newUser){
        String encodePassword = passwordUtils.encodePassword(newUser.getPassword());
        newUser.setPassword(encodePassword);
        userRepository.save(newUser);
    }

}
