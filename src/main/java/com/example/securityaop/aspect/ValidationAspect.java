package com.example.securityaop.aspect;

import com.example.securityaop.model.LoginRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class ValidationAspect {

    @Before("execution(* com.example.securityaop.controller.AuthController.login(..)) && args(req)")
    public void validateLogin(LoginRequest req) {
        if (req == null || req.getEmail() == null || req.getEmail().isBlank()
            || req.getPassword() == null || req.getPassword().isBlank()) {
            throw new IllegalArgumentException("Invalid login input");
        }
        if (!req.getEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        System.out.println("[VALIDATION] login input OK");
    }
}
