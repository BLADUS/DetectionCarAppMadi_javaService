package com.example.DetectionCarAppMadi_javaService.Util;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtils {
    public String encodePassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean verifyPassword(String password, String encodedPassword) {
        return BCrypt.checkpw(password, encodedPassword);
    }
}
