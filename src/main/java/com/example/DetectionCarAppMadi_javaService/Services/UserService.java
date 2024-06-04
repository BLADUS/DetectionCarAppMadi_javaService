package com.example.DetectionCarAppMadi_javaService.Services;

import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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

    public void saveUser(User newUser) {
        String encodePassword = passwordUtils.encodePassword(newUser.getPassword());
        newUser.setPassword(encodePassword);
        userRepository.save(newUser);
    }

    public User findByUsername(String username) {
        Optional<User> findUser = userRepository.findByUsername(username);
        return findUser.orElse(null); 
    }

    public boolean verifyPassword(String password, String encodedPassword) {
        return passwordUtils.verifyPassword(password, encodedPassword);
    }
}

