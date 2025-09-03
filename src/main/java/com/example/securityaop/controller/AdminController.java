package com.example.securityaop.controller;

import com.example.securityaop.annotation.RequireRoles;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @RequireRoles({"ADMIN"})
    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam String email) {
        return "User deleted: " + email;
    }
}
