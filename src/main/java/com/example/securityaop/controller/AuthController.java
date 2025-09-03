package com.example.securityaop.controller;

import com.example.securityaop.model.LoginRequest;
import com.example.securityaop.security.SecurityContext;
import com.example.securityaop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest req) {
        if (userService.checkPassword(req.getEmail(), req.getPassword())) {
            SecurityContext.set(req.getEmail(), userService.getRoles(req.getEmail()));
            return ResponseEntity.ok("Login OK: " + req.getEmail());
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        SecurityContext.clear();
        return ResponseEntity.ok("Logged out");
    }
}
