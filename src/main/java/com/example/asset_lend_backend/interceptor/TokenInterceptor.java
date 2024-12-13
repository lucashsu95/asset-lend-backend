package com.example.asset_lend_backend.interceptor;

import com.example.asset_lend_backend.config.SecurityConfig;
import com.example.asset_lend_backend.response.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.asset_lend_backend.model.User;
import com.example.asset_lend_backend.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String header = request.getHeader("Authorization");
        String token = header != null && header.startsWith("Bearer ") ? header.split(" ")[1] : null;

        if (token == null || !securityConfig.validateToken(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(new ObjectMapper().writeValueAsString(ApiResponse.INVALID_ACCESS_TOKEN().getBody()));
            return false;
        }

        String email = securityConfig.getEmailFromToken(token);
        User user = userRepository.findByEmail(email);
        if (user == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(new ObjectMapper().writeValueAsString(ApiResponse.USER_NOT_FOUND().getBody()));
            return false;
        }

        request.setAttribute("currentUser", user);
        return true;
    }
}