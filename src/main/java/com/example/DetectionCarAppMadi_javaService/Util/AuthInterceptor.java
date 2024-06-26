package com.example.DetectionCarAppMadi_javaService.Util;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@Component
public class AuthInterceptor implements HandlerInterceptor {

    @SuppressWarnings("null")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("currentUser") == null) {
            response.sendRedirect("/login");
            return false;
        }
        return true;

    }

}
    